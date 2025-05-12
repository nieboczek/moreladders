package nieboczek.moreladders.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import nieboczek.moreladders.MoreLadders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Unique
    private static final TagKey<Block> MAKE_TRAPDOOR_CLIMBABLE_LADDERS = TagKey.create(BuiltInRegistries.BLOCK.key(), MoreLadders.id("make_trapdoor_climbable_ladders"));

    private LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
        throw new AssertionError();
    }

    @Inject(method = "trapdoorUsableAsLadder", at = @At("HEAD"), cancellable = true)
    private void trapdoorUsableAsLadder(BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getValue(TrapDoorBlock.OPEN)) {
            BlockState blockState = level().getBlockState(pos.below());
            cir.setReturnValue(blockState.is(MAKE_TRAPDOOR_CLIMBABLE_LADDERS) && blockState.getValue(LadderBlock.FACING) == state.getValue(TrapDoorBlock.FACING));
            return;
        }
        cir.setReturnValue(false);
    }
}
