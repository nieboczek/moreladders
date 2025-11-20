package nieboczek.moreladders;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import nieboczek.moreladders.block.MLBlocks;

public final class MoreLaddersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(MLBlocks.COPPER_LADDER, MLBlocks.EXPOSED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(MLBlocks.EXPOSED_COPPER_LADDER, MLBlocks.WEATHERED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(MLBlocks.WEATHERED_COPPER_LADDER, MLBlocks.OXIDIZED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(MLBlocks.COPPER_LADDER, MLBlocks.WAXED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(MLBlocks.EXPOSED_COPPER_LADDER, MLBlocks.WAXED_EXPOSED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(MLBlocks.WEATHERED_COPPER_LADDER, MLBlocks.WAXED_WEATHERED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(MLBlocks.OXIDIZED_COPPER_LADDER, MLBlocks.WAXED_OXIDIZED_COPPER_LADDER);

        MLBlocks.BLOCKS.forEach((id, block) -> {
            Registry.register(BuiltInRegistries.BLOCK, id, block);
            Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        });
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MLCreativeModeTab.ID, MLCreativeModeTab.TAB);
    }
}
