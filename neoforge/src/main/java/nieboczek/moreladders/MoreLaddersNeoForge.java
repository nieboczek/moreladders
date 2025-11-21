package nieboczek.moreladders;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;
import nieboczek.moreladders.block.MLBlocks;

@Mod(MoreLadders.MOD_ID)
@EventBusSubscriber(modid = MoreLadders.MOD_ID)
public final class MoreLaddersNeoForge {
    @SubscribeEvent
    private static void register(RegisterEvent event) {
        event.register(Registries.BLOCK, registry -> MLBlocks.BLOCKS.forEach(registry::register));
        event.register(Registries.ITEM, registry -> MLBlocks.BLOCKS.forEach((id, block) ->
                registry.register(id, new BlockItem(block, new Item.Properties()))
        ));
        event.register(Registries.CREATIVE_MODE_TAB, registry ->
                registry.register(MLCreativeModeTab.ID, MLCreativeModeTab.TAB)
        );
    }
}