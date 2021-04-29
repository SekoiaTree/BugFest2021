package sekoia.bugfest.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EndermanBlockFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanBlockFeatureRenderer.class)
public class EndermanBlockRenderer {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void doNotRenderIfDead(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, EndermanEntity endermanEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (endermanEntity.isDead()) {
            ci.cancel();
        }
    }
}
