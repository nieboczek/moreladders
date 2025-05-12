package nieboczek.moreladders.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import nieboczek.moreladders.MoreLadders;
import nieboczek.moreladders.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(method = "evaluateNewBlockState", at = @At("HEAD"), cancellable = true)
    public void evaluateNewBlockState(Level level, BlockPos pos, Player player, BlockState state, UseOnContext ctx, CallbackInfoReturnable<Optional<BlockState>> cir) {
        Block block = state.getBlock();
        boolean success = false;

        if (block == ModBlocks.WAXED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.COPPER_LADDER.withPropertiesOf(state)));
        } else if (block == ModBlocks.WAXED_EXPOSED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.EXPOSED_COPPER_LADDER.withPropertiesOf(state)));
            success = true;
        } else if (block == ModBlocks.WAXED_WEATHERED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WEATHERED_COPPER_LADDER.withPropertiesOf(state)));
            success = true;
        } else if (block == ModBlocks.WAXED_OXIDIZED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.OXIDIZED_COPPER_LADDER.withPropertiesOf(state)));
            success = true;
        }

        if (success) {
            level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3004, pos, 0);
        }
    }
}
