package nieboczek.moreladders;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import nieboczek.moreladders.block.ModBlocks;

@Mod(MoreLadders.MOD_ID)
public class MoreLaddersNeoForge {
    public MoreLaddersNeoForge(IEventBus eventBus) {
        MoreLadders.init();

        eventBus.addListener(this::setupBuiltinResourcepack);
        eventBus.addListener(this::register);
    }

    private void setupBuiltinResourcepack(AddPackFindersEvent event) {
        event.addPackFinders(MoreLadders.id("resourcepacks/3d_ladders"), PackType.CLIENT_RESOURCES, Component.literal("3D Ladders"), PackSource.BUILT_IN, false, Pack.Position.TOP);
    }

    private void register(RegisterEvent event) {
        event.register(Registries.BLOCK, registry -> {
            ModBlocks.createBlocks();
            ModBlocks.BLOCKS.forEach((id, block) -> {
                //noinspection Convert2MethodRef
                registry.register(id, block);
            });
        });

        event.register(Registries.ITEM, registry -> {
            ModBlocks.BLOCKS.forEach((id, block) -> {
                registry.register(id, new BlockItem(block, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)).useBlockDescriptionPrefix()));
            });
        });

        event.register(Registries.CREATIVE_MODE_TAB, registry -> {
            registry.register(MoreLadders.id("group"),
                    CreativeModeTab.builder()
                            .title(Component.literal("MoreLadders"))
                            .icon(() -> new ItemStack(ModBlocks.SPRUCE_LADDER))
                            .displayItems((itemDisplayParameters, output) -> {
                                ModBlocks.BLOCKS.forEach((id, block) -> {
                                    output.accept(block);
                                });
                            }).build()
            );
        });
    }
}