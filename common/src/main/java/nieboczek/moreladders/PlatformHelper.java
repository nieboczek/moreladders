package nieboczek.moreladders;

import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface PlatformHelper {
    LadderBlock newLadderBlock(BlockBehaviour.Properties properties);
}
