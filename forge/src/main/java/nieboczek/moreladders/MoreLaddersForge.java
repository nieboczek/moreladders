package nieboczek.moreladders;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import nieboczek.moreladders.block.ModBlocks;

import java.util.Optional;

@Mod(MoreLadders.MOD_ID)
public class MoreLaddersForge {
    public MoreLaddersForge(IEventBus eventBus) {
        MoreLadders.init();

        eventBus.addListener(this::setupBuiltinResourcepack);
        eventBus.addListener(this::register);
    }

    private void setupBuiltinResourcepack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            PackLocationInfo info = new PackLocationInfo("3d_ladders", Component.literal("Credit to Vanilla Tweaks for vanilla 3D ladder."), PackSource.BUILT_IN, Optional.empty());

            event.addRepositorySource(consumer -> {
                consumer.accept(Pack.readMetaAndCreate(info,
                        new PathPackResources.PathResourcesSupplier(ModList.get().getModFileById(MoreLadders.MOD_ID).getFile().findResource("resourcepacks/3d_ladders")),
                        PackType.CLIENT_RESOURCES,
                        new PackSelectionConfig(false, Pack.Position.TOP, false)
                ));
            });
        }
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