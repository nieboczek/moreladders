package nieboczek.moreladders.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class OxidizableLadder extends LadderBlock implements WeatheringCopper {
//    public static final MapCodec<? extends OxidizableLadder> CODEC = RecordCodecBuilder.mapCodec(
//            block -> block.group(
//                    propertiesCodec(), WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(OxidizableLadder::getAge)
//            ).apply(block, OxidizableLadder::new)
//    );

    private final WeatherState weatherState;

    public OxidizableLadder(Properties properties, WeatherState weatherState) {
        super(properties);
        this.weatherState = weatherState;
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
}
