package sekoia.bugfest.mixin;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BeeEntity.class)
public class BeeStandingEyeHeight extends PassiveEntity {
    protected BeeStandingEyeHeight(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Sekoia
     */
    @Overwrite()
    public float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return this.isBaby() ? dimensions.height * 0.25F : dimensions.height * 0.5F;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
