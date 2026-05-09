package nieboczek.moreladders.platform;

import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class NeoForgePlatform implements IPlatform {
    @Override
    public LadderBlock newLadderBlock(BlockBehaviour.Properties properties) {
        return new LadderBlock(properties);
    }
}
