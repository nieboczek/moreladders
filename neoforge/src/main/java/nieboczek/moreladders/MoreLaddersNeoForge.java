package nieboczek.moreladders;


import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import nieboczek.moreladders.block.MLBlocks;

import java.util.Map;

@Mod(MoreLadders.MOD_ID)
@EventBusSubscriber(modid = MoreLadders.MOD_ID)
public final class MoreLaddersNeoForge {
    // TODO(neoforge): stripping oxidation doesn't produce particles and sound
    // TODO(neoforge): unwaxing doesn't produce particles and sound
    // TODO(neoforge): waxing doesn't work

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

    @SubscribeEvent
    private static void modifyTools(BlockEvent.BlockToolModificationEvent event) {
        ItemStack stack = event.getHeldItemStack();
        BlockState state = event.getFinalState();

        if (stack.is(ItemTags.AXES)) {
            for (Map.Entry<Block, Block> entry : MLBlocks.INVERSE_OXIDIZING.entrySet()) {
                if (state.is(entry.getKey())) {
                    event.setFinalState(entry.getValue().withPropertiesOf(state));
                    return;
                }
            }
            for (Map.Entry<Block, Block> entry : MLBlocks.UNWAXING.entrySet()) {
                if (state.is(entry.getKey())) {
                    event.setFinalState(entry.getValue().withPropertiesOf(state));
                    return;
                }
            }
        }
    }
}