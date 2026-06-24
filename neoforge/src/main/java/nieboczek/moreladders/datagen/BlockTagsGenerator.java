package nieboczek.moreladders.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import nieboczek.moreladders.MoreLadders;
import nieboczek.moreladders.block.MLBlockTags;
import nieboczek.moreladders.block.MLBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class BlockTagsGenerator extends BlockTagsProvider {
    BlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreLadders.MOD_ID);
    }

    private ResourceKey<Block> getResourceKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        List<ResourceKey<Block>> ladders = MLBlocks.BLOCKS.values().stream().map(this::getResourceKey).toList();

        tag(MLBlockTags.LADDERS).addAll(ladders);
        tag(BlockTags.CLIMBABLE).addAll(ladders);

        Stream<ResourceKey<Block>> mineableWithAxe = Arrays.stream(new Block[]{
                MLBlocks.SPRUCE_LADDER, MLBlocks.BIRCH_LADDER, MLBlocks.DARK_OAK_LADDER, MLBlocks.JUNGLE_LADDER, MLBlocks.WARPED_LADDER,
                MLBlocks.CRIMSON_LADDER, MLBlocks.ACACIA_LADDER, MLBlocks.CHERRY_LADDER, MLBlocks.BAMBOO_LADDER, MLBlocks.MANGROVE_LADDER,
                MLBlocks.PALE_OAK_LADDER
        }).map(this::getResourceKey);

        Stream<ResourceKey<Block>> mineableWithPickaxe = Arrays.stream(new Block[]{
                MLBlocks.IRON_LADDER, MLBlocks.CHAIN_LADDER, MLBlocks.GOLD_LADDER, MLBlocks.COPPER_LADDER, MLBlocks.EXPOSED_COPPER_LADDER,
                MLBlocks.WEATHERED_COPPER_LADDER, MLBlocks.OXIDIZED_COPPER_LADDER, MLBlocks.WAXED_COPPER_LADDER, MLBlocks.WAXED_EXPOSED_COPPER_LADDER,
                MLBlocks.WAXED_WEATHERED_COPPER_LADDER, MLBlocks.WAXED_OXIDIZED_COPPER_LADDER
        }).map(this::getResourceKey);

        tag(BlockTags.MINEABLE_WITH_AXE).addAll(mineableWithAxe);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addAll(mineableWithPickaxe);
    }
}
