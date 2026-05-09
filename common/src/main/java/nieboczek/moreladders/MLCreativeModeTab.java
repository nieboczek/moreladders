package nieboczek.moreladders;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import nieboczek.moreladders.block.MLBlocks;

public final class MLCreativeModeTab {
    public static final Identifier ID = MoreLadders.id("main");
    public static final CreativeModeTab TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.moreladders.main"))
            .icon(() -> new ItemStack(MLBlocks.SPRUCE_LADDER))
            .displayItems((_, output) -> {
                output.accept(MLBlocks.SPRUCE_LADDER);
                output.accept(MLBlocks.BIRCH_LADDER);
                output.accept(MLBlocks.DARK_OAK_LADDER);
                output.accept(MLBlocks.JUNGLE_LADDER);
                output.accept(MLBlocks.WARPED_LADDER);
                output.accept(MLBlocks.CRIMSON_LADDER);
                output.accept(MLBlocks.IRON_LADDER);
                output.accept(MLBlocks.ACACIA_LADDER);
                output.accept(MLBlocks.CHAIN_LADDER);
                output.accept(MLBlocks.CHERRY_LADDER);
                output.accept(MLBlocks.BAMBOO_LADDER);
                output.accept(MLBlocks.MANGROVE_LADDER);
                output.accept(MLBlocks.GOLD_LADDER);
                output.accept(MLBlocks.PALE_OAK_LADDER);
                output.accept(MLBlocks.COPPER_LADDER);
                output.accept(MLBlocks.EXPOSED_COPPER_LADDER);
                output.accept(MLBlocks.WEATHERED_COPPER_LADDER);
                output.accept(MLBlocks.OXIDIZED_COPPER_LADDER);
                output.accept(MLBlocks.WAXED_COPPER_LADDER);
                output.accept(MLBlocks.WAXED_EXPOSED_COPPER_LADDER);
                output.accept(MLBlocks.WAXED_WEATHERED_COPPER_LADDER);
                output.accept(MLBlocks.WAXED_OXIDIZED_COPPER_LADDER);
            })
            .build();

    private MLCreativeModeTab() {
    }
}
