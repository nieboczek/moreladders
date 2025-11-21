package nieboczek.moreladders.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import nieboczek.moreladders.MoreLadders;
import nieboczek.moreladders.block.MLBlocks;

public final class BlockStateGenerator extends BlockStateProvider {
    BlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MoreLadders.MOD_ID, exFileHelper);
    }

    private void ladder(Block block) {
        String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
        ModelFile model = models().withExistingParent(path, modLoc("block/base_ladder"))
                .texture("texture", blockTexture(block))
                .texture("particle", blockTexture(block));

        horizontalBlock(block, model);
        itemModels().withExistingParent(path, mcLoc("item/generated"))
                .texture("layer0", blockTexture(block));
    }

    private void deriveFromUnwaxed(Block waxed, Block unwaxed) {
        String waxedPath = BuiltInRegistries.BLOCK.getKey(waxed).getPath();
        ModelFile unwaxedModel = models().getExistingFile(BuiltInRegistries.BLOCK.getKey(unwaxed));

        horizontalBlock(waxed, unwaxedModel);
        itemModels().withExistingParent(waxedPath, mcLoc("item/generated"))
                .texture("layer0", blockTexture(unwaxed));
    }

    @Override
    protected void registerStatesAndModels() {
        ladder(MLBlocks.SPRUCE_LADDER);
        ladder(MLBlocks.BIRCH_LADDER);
        ladder(MLBlocks.DARK_OAK_LADDER);
        ladder(MLBlocks.JUNGLE_LADDER);
        ladder(MLBlocks.WARPED_LADDER);
        ladder(MLBlocks.CRIMSON_LADDER);
        ladder(MLBlocks.IRON_LADDER);
        ladder(MLBlocks.ACACIA_LADDER);
        ladder(MLBlocks.CHAIN_LADDER);
        ladder(MLBlocks.CHERRY_LADDER);
        ladder(MLBlocks.BAMBOO_LADDER);
        ladder(MLBlocks.MANGROVE_LADDER);
        ladder(MLBlocks.GOLD_LADDER);
        ladder(MLBlocks.PALE_OAK_LADDER);
        ladder(MLBlocks.COPPER_LADDER);
        ladder(MLBlocks.EXPOSED_COPPER_LADDER);
        ladder(MLBlocks.WEATHERED_COPPER_LADDER);
        ladder(MLBlocks.OXIDIZED_COPPER_LADDER);
        deriveFromUnwaxed(MLBlocks.WAXED_COPPER_LADDER, MLBlocks.COPPER_LADDER);
        deriveFromUnwaxed(MLBlocks.WAXED_EXPOSED_COPPER_LADDER, MLBlocks.EXPOSED_COPPER_LADDER);
        deriveFromUnwaxed(MLBlocks.WAXED_WEATHERED_COPPER_LADDER, MLBlocks.WEATHERED_COPPER_LADDER);
        deriveFromUnwaxed(MLBlocks.WAXED_OXIDIZED_COPPER_LADDER, MLBlocks.OXIDIZED_COPPER_LADDER);
    }
}
