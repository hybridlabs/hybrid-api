package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseDolphinEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseDolphinEntityModel<T : BaseDolphinEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
            namespace,
            "geo/mammal/$id/baby_$id.geo.json")
        } else {
            Identifier.fromNamespaceAndPath(
            namespace,
            "geo/mammal/$id/$id.geo.json")
        }
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/mammal/$id/baby_$id.png")
        } else {
            Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/mammal/$id/$id.png")
        }
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "animations/entity/mammal/$id/$id.animation.json")
    }

    fun getLayerTextureResource(renderState: GeoRenderState, layer: String): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/mammal/$id/layers/baby_${id}_$layer.png")
        } else {
            Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/mammal/$id/layers/${id}_$layer.png")
        }
    }
}
