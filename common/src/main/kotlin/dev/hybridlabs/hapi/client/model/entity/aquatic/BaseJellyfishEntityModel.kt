package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseJellyfishEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseJellyfishEntityModel<T : BaseJellyfishEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "jellyfish/$id/$id")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/jellyfish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "entity/jellyfish/$id/$id")
    }
}
