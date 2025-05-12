package nieboczek.moreladders.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class OxidizableLadder extends LadderBlock implements WeatheringCopper {
    private final WeatherState weatherState;

    public OxidizableLadder(Properties properties, WeatherState state) {
        super(properties.sound(SoundType.COPPER));
        weatherState = state;
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }
}
