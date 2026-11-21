package dev.hybridlabs.hapi.client.model

import dev.hybridlabs.hapi.entity.base.VariantHolder
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.world.entity.Entity
import com.geckolib.constant.dataticket.DataTicket
import com.geckolib.renderer.base.GeoRenderState
import net.minecraft.world.item.ItemStack
import java.util.UUID

/**
 * DataTickets used by the HAPI base renderers to hand entity state to the models and bone updates.
 * <p>
 * GeckoLib 5 decouples models and renderers: the model only sees a render state, so the renderer
 * captures entity data in [com.geckolib.renderer.base.GeoRenderer.addRenderData] and the
 * model reads it back through these tickets.
 */
object HAPIDataTickets {
    /** Whether the entity being rendered is a baby. */
    @JvmField
    val IS_BABY: DataTicket<Boolean> = DataTicket.create("is_baby", Boolean::class.java)

    /** The lerped pitch tilt of the entity, in degrees. */
    @JvmField
    val TILT: DataTicket<Float> = DataTicket.create("tilt", Float::class.java)

    /** The lerped turn roll of the entity, in degrees. */
    @JvmField
    val ROLL: DataTicket<Float> = DataTicket.create("roll", Float::class.java)

    /** The scale adjustment of variable-size entities, relative to a fully grown one. */
    @JvmField
    val SIZE: DataTicket<Float> = DataTicket.create("size", Float::class.java)

    /** The lerped tilt angle of jellyfish-like entities, in degrees. */
    @JvmField
    val TILT_ANGLE: DataTicket<Float> = DataTicket.create("tilt_angle", Float::class.java)

    /** The item in the entity's main hand, for held-item rendering. */
    @JvmField
    val MAIN_HAND_ITEM: DataTicket<ItemStack> = DataTicket.create("main_hand_item", ItemStack::class.java)

    /** The ordinal of the entity's [VariantHolder] variant, for models that pick resources by variant. */
    @JvmField
    val VARIANT: DataTicket<Int> = DataTicket.create("variant", Int::class.java)

    /** The rendered entity's uuid, for models that pick resources deterministically per entity. */
    @JvmField
    val ENTITY_UUID: DataTicket<UUID> = DataTicket.create("entity_uuid", UUID::class.java)

    /** The rendered water animal's moistness, for models that change appearance when drying out. */
    @JvmField
    val MOISTNESS: DataTicket<Int> = DataTicket.create("moistness", Int::class.java)

    /**
     * Captures the entity state models need to select resources from the render state. The variant is
     * stored as its enum ordinal; non-enum variants are ignored.
     */
    fun captureEntityState(animatable: Any, renderState: GeoRenderState) {
        val variant = (animatable as? VariantHolder<*>)?.variant

        if (variant is Enum<*>) {
            renderState.addGeckolibData(VARIANT, variant.ordinal)
        }

        if (animatable is Entity) {
            renderState.addGeckolibData(ENTITY_UUID, animatable.uuid)
        }

        if (animatable is BaseWaterAnimal) {
            renderState.addGeckolibData(MOISTNESS, animatable.moistness)
        }
    }
}

/** Resolves the captured [HAPIDataTickets.VARIANT] ordinal back into an enum constant. */
fun <T : Enum<T>> GeoRenderState.getVariant(entries: List<T>): T? =
    getGeckolibData(HAPIDataTickets.VARIANT)?.let(entries::getOrNull)
