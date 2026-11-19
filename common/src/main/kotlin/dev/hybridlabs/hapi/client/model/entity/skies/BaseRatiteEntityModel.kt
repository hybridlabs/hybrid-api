package dev.hybridlabs.hapi.client.model.entity.skies

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.skies.BaseRatiteEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseRatiteEntityModel<T: BaseRatiteEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
                namespace,
                "geo/entity/$id/baby_$id.geo.json")
        } else {
            Identifier.fromNamespaceAndPath(
                namespace,
                "geo/entity/$id/$id.geo.json")
        }
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
                namespace,
                "textures/entity/$id/baby_$id.png")
        } else {
            Identifier.fromNamespaceAndPath(
                namespace,
                "textures/entity/$id/$id.png")
        }
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return if (animatable.isBaby) {
            Identifier.fromNamespaceAndPath(
                namespace,
                "animations/entity/$id/baby_$id.animation.json")
        } else {
            Identifier.fromNamespaceAndPath(
                namespace,
                "animations/entity/$id/$id.animation.json")
        }
    }
}
