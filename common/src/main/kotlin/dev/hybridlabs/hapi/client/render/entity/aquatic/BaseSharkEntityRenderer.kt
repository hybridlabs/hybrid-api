package dev.hybridlabs.hapi.client.render.entity.aquatic

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.util.Mth
import com.geckolib.animation.state.BoneSnapshot
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoEntityRenderer
import com.geckolib.renderer.base.BoneSnapshots
import com.geckolib.renderer.base.GeoRenderState
import com.geckolib.renderer.base.RenderPassInfo
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer
import kotlin.math.abs

@Suppress("LeakingThis")
open class BaseSharkEntityRenderer<T : BaseSharkEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false
) : GeoEntityRenderer<T, R>(context, model) {

    init {
        if (canGlow) withRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 180f
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
        renderState.addGeckolibData(
            HAPIDataTickets.SIZE,
            if (variableSize) BaseWaterAnimal.getScaleAdjustment(animatable, 0.05f) else 1f
        )

        if (animatable.moistness <= -20 && !animatable.isInWater) {
            return
        }

        renderState.addGeckolibData(
            HAPIDataTickets.TILT,
            Mth.clamp(Mth.lerp(partialTick, animatable.xRotO, animatable.xRot), -35f, 35f)
        )
        renderState.addGeckolibData(HAPIDataTickets.ROLL, turnRoll(animatable, partialTick))
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val tilt = renderPassInfo.getGeckolibData(HAPIDataTickets.TILT) ?: return
        val roll = renderPassInfo.getGeckolibData(HAPIDataTickets.ROLL) ?: return

        snapshots.get(PartNames.HEAD).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * -Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.BODY).ifPresent { bone: BoneSnapshot ->
            bone.setRotX(tilt * -Mth.DEG_TO_RAD)
        }
        snapshots.get("torso").ifPresent { bone: BoneSnapshot ->
            bone.setRotZ(roll * -Mth.DEG_TO_RAD)
        }
        snapshots.get("torso_2").ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.TAIL).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.TAIL_FIN).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
    }

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    companion object {
        /** Smoothed turn roll for the current render pass, updating the entity's roll state. */
        fun turnRoll(animatable: BaseSharkEntity, partialTick: Float): Float {
            val yawDiff = animatable.yRot - animatable.yRotO
            val targetRoll = Mth.clamp(yawDiff * 3f, -30f, 30f)

            val turnSpeed = abs(yawDiff)
            val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
            animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

            return Mth.clamp(Mth.lerp(partialTick, animatable.prevRoll, animatable.currentRoll), -30f, 30f)
        }
    }
}
