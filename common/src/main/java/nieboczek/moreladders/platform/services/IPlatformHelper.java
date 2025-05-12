package nieboczek.moreladders.platform.services;

import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface IPlatformHelper {
    LadderBlock newLadderBlock(BlockBehaviour.Properties properties);
}