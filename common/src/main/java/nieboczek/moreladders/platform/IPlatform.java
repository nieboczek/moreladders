package nieboczek.moreladders.platform;

import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface IPlatform {
    LadderBlock newLadderBlock(BlockBehaviour.Properties properties);
}
