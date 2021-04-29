package sekoia.bugfest.mixin;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieVillagerEntity.class)
public class GoldenAppleConsuming {
    @Inject(method = "interactMob", at = @At("RETURN"), cancellable = true)
    private void changeGoldenAppleConsuming(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (cir.getReturnValue() == ActionResult.CONSUME) {
            cir.setReturnValue(ActionResult.PASS);
        }
    }
}
