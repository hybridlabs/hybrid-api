package dev.hybridlabs.hapi.client.model.entity.skies

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.skies.BaseFlyingAnimal
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseFlyingAnimalEntityModel<T : BaseFlyingAnimal>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if (renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true) {
            Identifier.fromNamespaceAndPath(
                namespace,
                "entity/$id/baby_$id")
        } else {
            Identifier.fromNamespaceAndPath(
                namespace,
                "entity/$id/$id")
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
                "entity/$id/baby_$id")
        } else {
            Identifier.fromNamespaceAndPath(
                namespace,
                "entity/$id/$id")
        }
    }
}
