package nieboczek.moreladders.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import nieboczek.moreladders.MoreLadders;
import nieboczek.moreladders.block.MLBlockTags;
import nieboczek.moreladders.block.MLBlocks;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public final class BlockTagsGenerator extends BlockTagsProvider {
    BlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreLadders.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        Block[] ladders = MLBlocks.BLOCKS.values().toArray(Block[]::new);

        tag(MLBlockTags.LADDERS).add(ladders);
        tag(BlockTags.CLIMBABLE).add(ladders);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(MLBlocks.SPRUCE_LADDER, MLBlocks.BIRCH_LADDER, MLBlocks.DARK_OAK_LADDER, MLBlocks.JUNGLE_LADDER, MLBlocks.WARPED_LADDER)
                .add(MLBlocks.CRIMSON_LADDER, MLBlocks.ACACIA_LADDER, MLBlocks.CHERRY_LADDER, MLBlocks.BAMBOO_LADDER, MLBlocks.MANGROVE_LADDER)
                .add(MLBlocks.PALE_OAK_LADDER);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MLBlocks.IRON_LADDER, MLBlocks.CHAIN_LADDER, MLBlocks.GOLD_LADDER, MLBlocks.COPPER_LADDER, MLBlocks.EXPOSED_COPPER_LADDER)
                .add(MLBlocks.WEATHERED_COPPER_LADDER, MLBlocks.OXIDIZED_COPPER_LADDER, MLBlocks.WAXED_COPPER_LADDER, MLBlocks.WAXED_EXPOSED_COPPER_LADDER)
                .add(MLBlocks.WAXED_WEATHERED_COPPER_LADDER, MLBlocks.WAXED_OXIDIZED_COPPER_LADDER);
    }
}
