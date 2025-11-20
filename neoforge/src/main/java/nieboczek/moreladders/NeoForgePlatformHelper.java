package nieboczek.moreladders;

import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class NeoForgePlatformHelper implements PlatformHelper {
    @Override
    public LadderBlock newLadderBlock(BlockBehaviour.Properties properties) {
        return new LadderBlock(properties);
    }
}
