package nieboczek.moreladders.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import nieboczek.moreladders.block.MLBlocks;

import java.util.concurrent.CompletableFuture;

public final class RecipeGenerator extends RecipeProvider {
    RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private static String getCriterionName(ItemLike item) {
        return "has_" + BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }

    private static void ladder(RecipeOutput output, ItemLike inner, ItemLike outer, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .define('|', outer)
                .define('#', inner)
                .pattern("| |")
                .pattern("|#|")
                .pattern("| |")
                .unlockedBy(getCriterionName(inner), has(inner))
                .save(output);
    }

    private static void wax(RecipeOutput output, ItemLike input, ItemLike result) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(input)
                .requires(Items.HONEYCOMB)
                .unlockedBy(getCriterionName(input), has(input))
                .save(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
         ladder(output, Items.SPRUCE_PLANKS, Items.STICK, MLBlocks.SPRUCE_LADDER);
         ladder(output, Items.BIRCH_PLANKS, Items.STICK, MLBlocks.BIRCH_LADDER);
         ladder(output, Items.DARK_OAK_PLANKS, Items.STICK, MLBlocks.DARK_OAK_LADDER);
         ladder(output, Items.JUNGLE_PLANKS, Items.STICK, MLBlocks.JUNGLE_LADDER);
         ladder(output, Items.WARPED_PLANKS, Items.STICK, MLBlocks.WARPED_LADDER);
         ladder(output, Items.CRIMSON_PLANKS, Items.STICK, MLBlocks.CRIMSON_LADDER);
         ladder(output, Items.IRON_INGOT, Items.IRON_NUGGET, MLBlocks.IRON_LADDER);
         ladder(output, Items.ACACIA_PLANKS, Items.STICK, MLBlocks.ACACIA_LADDER);
         ladder(output, Items.CHAIN, Items.IRON_NUGGET, MLBlocks.CHAIN_LADDER);
         ladder(output, Items.CHERRY_PLANKS, Items.STICK, MLBlocks.CHERRY_LADDER);
         ladder(output, Items.BAMBOO_PLANKS, Items.BAMBOO, MLBlocks.BAMBOO_LADDER);
         ladder(output, Items.MANGROVE_PLANKS, Items.STICK, MLBlocks.MANGROVE_LADDER);
         ladder(output, Items.GOLD_INGOT, Items.GOLD_NUGGET, MLBlocks.GOLD_LADDER);
         ladder(output, Items.COPPER_INGOT, Items.COPPER_INGOT, MLBlocks.COPPER_LADDER);

         wax(output, MLBlocks.COPPER_LADDER, MLBlocks.WAXED_COPPER_LADDER);
         wax(output, MLBlocks.EXPOSED_COPPER_LADDER, MLBlocks.WAXED_EXPOSED_COPPER_LADDER);
         wax(output, MLBlocks.WEATHERED_COPPER_LADDER, MLBlocks.WAXED_WEATHERED_COPPER_LADDER);
         wax(output, MLBlocks.WAXED_OXIDIZED_COPPER_LADDER, MLBlocks.WAXED_OXIDIZED_COPPER_LADDER);
    }
}
