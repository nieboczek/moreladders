package nieboczek.moreladders.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import nieboczek.moreladders.block.MLBlocks;

import java.util.Set;

public final class BlockLootGenerator extends BlockLootSubProvider {
    BlockLootGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MLBlocks.BLOCKS.values();
    }

    @Override
    protected void generate() {
        MLBlocks.BLOCKS.forEach((id, block) -> dropSelf(block));
    }
}
