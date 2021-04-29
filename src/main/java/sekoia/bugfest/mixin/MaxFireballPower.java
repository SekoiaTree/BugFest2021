package sekoia.bugfest.mixin;

import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireballEntity.class)
public class MaxFireballPower {
    @Shadow public int explosionPower;

    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void maxPower(CompoundTag tag, CallbackInfo ci) {
        if (this.explosionPower > 511) {
            this.explosionPower = 511;
        }
    }
}
