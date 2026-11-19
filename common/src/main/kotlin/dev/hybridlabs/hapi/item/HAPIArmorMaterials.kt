package dev.hybridlabs.hapi.item

import dev.hybridlabs.hapi.CommonClass
import dev.hybridlabs.hapi.tag.HAPIItemTags
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.EquipmentAssets

object HAPIArmorMaterials {
    private fun makeDefense(helmet: Int, chestplate: Int, leggings: Int, boots: Int, body: Int): Map<ArmorType, Int> {
        return mapOf(
            ArmorType.HELMET to helmet,
            ArmorType.CHESTPLATE to chestplate,
            ArmorType.LEGGINGS to leggings,
            ArmorType.BOOTS to boots,
            ArmorType.BODY to body
        )
    }

    private fun asset(name: String): ResourceKey<EquipmentAsset> {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, CommonClass.locate(name))
    }

    val DIVING = ArmorMaterial(
        11, makeDefense(2, 5, 4, 2, 5), 9, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0f, 0.0f,
        ItemTags.REPAIRS_COPPER_ARMOR, asset("diving")
    )

    val GLOWING_DIVING = ArmorMaterial(
        11, makeDefense(2, 5, 4, 2, 5), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0f, 0.0f,
        ItemTags.REPAIRS_COPPER_ARMOR, asset("glowing_diving")
    )

    val REINFORCED_DIVING = ArmorMaterial(
        15, makeDefense(3, 7, 5, 3, 5), 9, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0f, 1.0f,
        ItemTags.REPAIRS_COPPER_ARMOR, asset("reinforced_diving")
    )

    val SEASHELL = ArmorMaterial(
        25, makeDefense(2, 4, 3, 2, 4), 22, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0f, 0.0f,
        HAPIItemTags.REPAIRS_SEASHELL_ARMOR, asset("seashell")
    )

    val MANGLERFISH = ArmorMaterial(
        5, makeDefense(1, 1, 1, 1, 1), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0f, 0.0f,
        HAPIItemTags.GLOWSLIME, asset("manglerfish")
    )

    val EEL = ArmorMaterial(
        5, makeDefense(1, 1, 1, 1, 1), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0f, 0.0f,
        ItemTags.WOOL, asset("eel")
    )

    val HATXOLOTL = ArmorMaterial(
        5, makeDefense(1, 1, 1, 1, 1), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0f, 0.0f,
        ItemTags.REPAIRS_LEATHER_ARMOR, asset("hatxolotl")
    )

    val MOONJELLYFISH = ArmorMaterial(
        5, makeDefense(1, 1, 1, 1, 1), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0f, 0.0f,
        HAPIItemTags.REPAIRS_MOON_JELLY_ARMOR, asset("moon_jelly")
    )

    val TURTLE = ArmorMaterial(
        25, makeDefense(1, 1, 1, 1, 1), 9, SoundEvents.ARMOR_EQUIP_TURTLE, 1.0f, 0.3f,
        ItemTags.REPAIRS_TURTLE_HELMET, asset("turtle")
    )
}
