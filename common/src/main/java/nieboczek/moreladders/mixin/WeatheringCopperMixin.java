package nieboczek.moreladders.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import nieboczek.moreladders.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(WeatheringCopper.class)
public interface WeatheringCopperMixin {
    @Inject(method = "getNext(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private static void getNext(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        if (block == ModBlocks.COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.EXPOSED_COPPER_LADDER));
        } else if (block == ModBlocks.EXPOSED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WEATHERED_COPPER_LADDER));
        } else if (block == ModBlocks.WEATHERED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.OXIDIZED_COPPER_LADDER));
        }
    }

    @Inject(method = "getPrevious(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private static void getPrevious(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        if (block == ModBlocks.OXIDIZED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.WEATHERED_COPPER_LADDER));
        } else if (block == ModBlocks.WEATHERED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.EXPOSED_COPPER_LADDER));
        } else if (block == ModBlocks.EXPOSED_COPPER_LADDER) {
            cir.setReturnValue(Optional.of(ModBlocks.COPPER_LADDER));
        }
    }

    @Inject(method = "getFirst(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/Block;", at = @At("HEAD"), cancellable = true)
    private static void getFirst(Block block, CallbackInfoReturnable<Block> cir) {
        if (
            block == ModBlocks.COPPER_LADDER ||
            block == ModBlocks.EXPOSED_COPPER_LADDER ||
            block == ModBlocks.WEATHERED_COPPER_LADDER ||
            block == ModBlocks.OXIDIZED_COPPER_LADDER
        ) {
            cir.setReturnValue(ModBlocks.COPPER_LADDER);
        }
    }
}
