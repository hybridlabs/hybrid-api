package dev.hybridlabs.hapi.client.render.entity.skies

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.skies.BaseParrotEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.BoneSnapshot
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.RenderPassInfo

open class BaseParrotEntityRenderer<T : BaseParrotEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T, R>(context, model) {

    init {
        this.shadowRadius = 0.3f
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val headPitch = renderPassInfo.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f
        val netHeadYaw = renderPassInfo.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f) ?: 0f

        snapshots.get("head").ifPresent { bone: BoneSnapshot ->
            bone.setRotX(headPitch * Mth.DEG_TO_RAD)
            bone.setRotY(netHeadYaw * Mth.DEG_TO_RAD)
        }
    }
}
