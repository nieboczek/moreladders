package nieboczek.moreladders.mixin;

import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import nieboczek.moreladders.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(HoneycombItem.class)
public class HoneycombItemMixin {
    @Inject(method = "getWaxed", at = @At("HEAD"), cancellable = true)
    private static void getWaxed(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
        Block block = state.getBlock();

        if (block == ModBlocks.COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WAXED_COPPER_LADDER.withPropertiesOf(state)));
        } else if (block == ModBlocks.EXPOSED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WAXED_EXPOSED_COPPER_LADDER.withPropertiesOf(state)));
        } else if (block == ModBlocks.WEATHERED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WAXED_WEATHERED_COPPER_LADDER.withPropertiesOf(state)));
        } else if (block == ModBlocks.OXIDIZED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WAXED_OXIDIZED_COPPER_LADDER.withPropertiesOf(state)));
        }
    }
}
