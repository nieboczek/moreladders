package nieboczek.moreladders;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import nieboczek.moreladders.block.ModBlocks;

public class MoreLaddersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MoreLadders.init();
        ModBlocks.createBlocks();

        ModBlocks.BLOCKS.forEach((id, block) -> {
            Registry.register(BuiltInRegistries.BLOCK, id, block);
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
            Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
        });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MoreLadders.id("group"),
                FabricItemGroup.builder()
                        .title(Component.literal("MoreLadders"))
                        .icon(() -> new ItemStack(ModBlocks.SPRUCE_LADDER))
                        .displayItems(((itemDisplayParameters, output) -> {
                            ModBlocks.BLOCKS.forEach((id, block) -> {
                                output.accept(block);
                            });
                        })).build()
        );
    }
}
