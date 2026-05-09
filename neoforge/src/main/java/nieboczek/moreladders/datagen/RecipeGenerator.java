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
    RecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    private String getCriterionName(ItemLike item) {
        return "has_" + BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }

    private void ladder(ItemLike inner, ItemLike outer, ItemLike result) {
        shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .define('|', outer)
                .define('#', inner)
                .pattern("| |")
                .pattern("|#|")
                .pattern("| |")
                .unlockedBy(getCriterionName(inner), has(inner))
                .save(output);
    }

    private void wax(ItemLike input, ItemLike result) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(input)
                .requires(Items.HONEYCOMB)
                .unlockedBy(getCriterionName(input), has(input))
                .save(output);
    }

    @Override
    protected void buildRecipes() {
         ladder(Items.SPRUCE_PLANKS, Items.STICK, MLBlocks.SPRUCE_LADDER);
         ladder(Items.BIRCH_PLANKS, Items.STICK, MLBlocks.BIRCH_LADDER);
         ladder(Items.DARK_OAK_PLANKS, Items.STICK, MLBlocks.DARK_OAK_LADDER);
         ladder(Items.JUNGLE_PLANKS, Items.STICK, MLBlocks.JUNGLE_LADDER);
         ladder(Items.WARPED_PLANKS, Items.STICK, MLBlocks.WARPED_LADDER);
         ladder(Items.CRIMSON_PLANKS, Items.STICK, MLBlocks.CRIMSON_LADDER);
         ladder(Items.IRON_INGOT, Items.IRON_NUGGET, MLBlocks.IRON_LADDER);
         ladder(Items.ACACIA_PLANKS, Items.STICK, MLBlocks.ACACIA_LADDER);
         ladder(Items.IRON_CHAIN, Items.IRON_NUGGET, MLBlocks.CHAIN_LADDER);
         ladder(Items.CHERRY_PLANKS, Items.STICK, MLBlocks.CHERRY_LADDER);
         ladder(Items.BAMBOO_PLANKS, Items.BAMBOO, MLBlocks.BAMBOO_LADDER);
         ladder(Items.MANGROVE_PLANKS, Items.STICK, MLBlocks.MANGROVE_LADDER);
         ladder(Items.GOLD_INGOT, Items.GOLD_NUGGET, MLBlocks.GOLD_LADDER);
         ladder(Items.PALE_OAK_PLANKS, Items.STICK, MLBlocks.PALE_OAK_LADDER);
         ladder(Items.COPPER_INGOT, Items.COPPER_INGOT, MLBlocks.COPPER_LADDER);

         wax(MLBlocks.COPPER_LADDER, MLBlocks.WAXED_COPPER_LADDER);
         wax(MLBlocks.EXPOSED_COPPER_LADDER, MLBlocks.WAXED_EXPOSED_COPPER_LADDER);
         wax(MLBlocks.WEATHERED_COPPER_LADDER, MLBlocks.WAXED_WEATHERED_COPPER_LADDER);
         wax(MLBlocks.WAXED_OXIDIZED_COPPER_LADDER, MLBlocks.WAXED_OXIDIZED_COPPER_LADDER);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new RecipeGenerator(provider, output);
        }

        @Override
        public String getName() {
            return "More Ladders Recipes";
        }
    }
}
