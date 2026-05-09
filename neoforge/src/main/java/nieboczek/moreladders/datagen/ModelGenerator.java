package nieboczek.moreladders.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import nieboczek.moreladders.MoreLadders;
import nieboczek.moreladders.block.MLBlocks;

import java.util.Optional;
import java.util.function.Function;

public final class ModelGenerator extends ModelProvider {
    private BlockModelGenerators blockModels = null;
    private ItemModelGenerators itemModels = null;

    // honestly fuck this shit from the bottom of my heart, this is so much fucking harder than a scripting language
    // outputting the same JSON with substituted values to multiple files. LIKE LITERALLY, THINK ABOUT IT.
    // you don't have to worry about mojang refactoring the whole codebase every update, the model JSON schema hasn't
    // changed since like forever. FUCK FUCK FUCK stupid shit

    // hours wasted: 6
    // THIS IS LIKE ONE OF THE SIMPLEST MODS AND I NEED TO DO ALL OF THIS WEIRD SHIT

    private ModelTemplate ladderTemplate = new ModelTemplate(Optional.of(modLocation("block/base_ladder")), Optional.empty(), TextureSlot.TEXTURE);
    private Function<Block, TextureMapping> ladderMapper = block -> new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block));
    private TexturedModel.Provider ladderProvider = block -> new TexturedModel(ladderMapper.apply(block), ladderTemplate);

    ModelGenerator(PackOutput output) {
        super(output, MoreLadders.MOD_ID);
    }

    private void ladder(Block block) {
        blockModels.createHorizontallyRotatedBlock(
                block,
                // TODO: +waterlogged=false/true (idk if required)
                ladderProvider
        );

        itemModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(
                ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layer0(block), itemModels.modelOutput)
        ));
    }

    private void deriveFromUnwaxed(Block waxed, Block unwaxed) {
        MultiVariant model = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(unwaxed));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(waxed, model).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
        itemModels.itemModelOutput.accept(waxed.asItem(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(unwaxed.asItem())));
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.blockModels = blockModels;
        this.itemModels = itemModels;

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
