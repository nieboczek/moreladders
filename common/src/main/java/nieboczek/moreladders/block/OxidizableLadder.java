package nieboczek.moreladders.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nieboczek.moreladders.MoreLadders;

import java.util.Optional;

public class OxidizableLadder extends Block implements WeatheringCopper {
    public static final MapCodec<OxidizableLadder> CODEC = RecordCodecBuilder.mapCodec(
            block -> block.group(
                    propertiesCodec(), WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(OxidizableLadder::getAge)
            ).apply(block, OxidizableLadder::new)
    );

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape NORTH_SHAPE = Block.box(0, 0, 13, 16, 16, 16);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0, 0, 0, 16, 16, 3);
    private static final VoxelShape WEST_SHAPE = Block.box(13, 0, 0, 16, 16, 16);
    private static final VoxelShape EAST_SHAPE = Block.box(0, 0, 0, 3, 16, 16);

    private final WeatherState weatherState;

    public OxidizableLadder(Properties properties, WeatherState weatherState) {
        super(properties);
        this.weatherState = weatherState;
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends OxidizableLadder> codec() {
        return CODEC;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return getNext(state).isPresent();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        changeOverTime(state, level, pos, random);
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> EAST_SHAPE;
        };
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos relativePos = pos.relative(direction.getOpposite());

        BlockState relativeState = level.getBlockState(relativePos);
        return relativeState.isFaceSturdy(level, relativePos, direction);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (state.getValue(WATERLOGGED)) {
                level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }

            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (!context.replacingClickedOnBlock()) {
            BlockState relativeState = level.getBlockState(pos.relative(context.getClickedFace().getOpposite()));
            if (relativeState.is(this) && relativeState.getValue(FACING) == context.getClickedFace()) {
                return null;
            }
        }

        FluidState fluidState = level.getFluidState(context.getClickedPos());
        BlockState state = defaultBlockState();

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                state = state.setValue(FACING, direction.getOpposite());
                if (state.canSurvive(level, pos)) {
                    return state.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
