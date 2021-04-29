package sekoia.bugfest.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntityRenderer.class)
public class EndermanPose {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/EndermanEntity;isAngry()Z"))
    private void wrongPoseFix(EndermanEntity endermanEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (endermanEntity.isDead()) {
            EndermanEntityModel<EndermanEntity> model = ((EndermanEntityRenderer) (Object) this).getModel();
            model.carryingBlock = false;
            model.rightArm.pitch = 0F;
            model.leftArm.pitch = 0F;
            model.rightArm.roll = 0F;
            model.leftArm.roll = 0F;
        }
    }
}
