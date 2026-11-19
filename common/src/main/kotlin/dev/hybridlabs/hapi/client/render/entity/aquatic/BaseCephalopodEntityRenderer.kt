package dev.hybridlabs.hapi.client.render.entity.aquatic

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCephalopodEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.BoneSnapshot
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class BaseCephalopodEntityRenderer<T : BaseCephalopodEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false
) : GeoEntityRenderer<T, R>(context, model) {

    init {
        if (canGlow) withRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
        renderState.addGeckolibData(
            HAPIDataTickets.SIZE,
            if (variableSize) BaseWaterAnimal.getScaleAdjustment(animatable, 0.05f) else 1f
        )
        renderState.addGeckolibData(
            HAPIDataTickets.TILT,
            Mth.clamp(Mth.lerp(partialTick, animatable.xRotO, animatable.xRot), -45f, 45f)
        )
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val tilt = renderPassInfo.getGeckolibData(HAPIDataTickets.TILT) ?: return

        snapshots.get(PartNames.BODY).ifPresent { bone: BoneSnapshot ->
            bone.setRotX(tilt * -Mth.DEG_TO_RAD)
        }
    }
}
