package nieboczek.moreladders.platform;

import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import nieboczek.moreladders.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public LadderBlock newLadderBlock(BlockBehaviour.Properties properties) {
        return new LadderBlock(properties);
    }
}
