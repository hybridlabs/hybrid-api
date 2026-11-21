package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseMammalEntity
import net.minecraft.resources.Identifier
import com.geckolib.model.GeoModel
import com.geckolib.renderer.base.GeoRenderState

abstract class BaseMammalEntityModel<T : BaseMammalEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
            namespace,
            "mammal/$id/baby_$id")
        } else {
            Identifier.fromNamespaceAndPath(
            namespace,
            "mammal/$id/$id")
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
            "entity/mammal/$id/$id")
    }

    fun getLayerTextureResource(layer: String): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/mammal/$id/layers/${id}_$layer.png")
    }
}
