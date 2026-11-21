package dev.hybridlabs.hapi.item

import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.ProblemReporter
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.item.component.TooltipDisplay
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.storage.TagValueInput
import net.minecraft.world.level.storage.TagValueOutput
import java.util.*
import java.util.function.Consumer

class CreatureNetItem(settings: Properties) : Item(settings) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipDisplay: TooltipDisplay,
        tooltipAdder: Consumer<Component>,
        flag: TooltipFlag
    ) {
        tooltipAdder.accept(
            Component.translatable("item.hapi.creature_net.function")
                .withStyle(ChatFormatting.GRAY)
        )
        tooltipAdder.accept(
            Component.translatable("item.hapi.creature_net.properties")
                .withStyle(ChatFormatting.GRAY)
        )

        val customData = stack.get(DataComponents.CUSTOM_DATA) ?: return

        val optionalEntity = getEntityFromNBT(customData)
        if (optionalEntity.isPresent) {
            val entityName = optionalEntity.get().description ?: return
            tooltipAdder.accept(
                Component.translatable(
                    "item.hapi.creature_net.description",
                    entityName
                )
            )
        }
    }

    override fun interactLivingEntity(
        stack: ItemStack,
        user: Player,
        entity: LivingEntity,
        hand: InteractionHand
    ): InteractionResult {
        val validFishForNet = entity.type.builtInRegistryHolder().`is`(HAPIEntityTags.NET_CATCHABLE)

        if (!alreadyHasFish(stack) && validFishForNet) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return InteractionResult.SUCCESS
        }
        return super.interactLivingEntity(stack, user, entity, hand)
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        val stack = player.getItemInHand(hand)

        if (level is ServerLevel) {
            val customData = stack.components.get(DataComponents.CUSTOM_DATA)
            if (customData != null) {
                val optionalEntity = getEntityFromNBT(customData)
                if (optionalEntity.isPresent) {
                    val hitResult =
                        getPlayerPOVHitResult(level, player, net.minecraft.world.level.ClipContext.Fluid.SOURCE_ONLY)
                    if (hitResult.type != net.minecraft.world.phys.HitResult.Type.BLOCK) {
                        return InteractionResult.PASS
                    }

                    val pos = hitResult.blockPos
                    val clickedFace = hitResult.direction
                    var spawnPos = pos.relative(clickedFace)

                    val fluidState = level.getFluidState(pos)
                    if (fluidState.`is`(Fluids.WATER)) {
                        spawnPos = pos
                    }

                    val entityType = optionalEntity.get()
                    val entity = entityType.create(level, EntitySpawnReason.TRIGGERED)
                        ?: return InteractionResult.FAIL
                    val customData2 =
                        stack.components.get(DataComponents.CUSTOM_DATA) ?: return InteractionResult.FAIL
                    val tag = customData2.copyTag()
                    val entityData = tag.getCompoundOrEmpty(ENTITY_KEY)
                    val input = TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), entityData)
                    entity.load(input)

                    entity.snapTo(
                        spawnPos.x + 0.5,
                        spawnPos.y + 0.1,
                        spawnPos.z + 0.5,
                        level.random.nextFloat() * 360f,
                        0f
                    )

                    level.addFreshEntity(entity)
                    stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY) { data ->
                        data.update { compoundTag -> compoundTag.remove(ENTITY_KEY) }
                    }

                    return InteractionResult.SUCCESS
                }
            }
        }

        return InteractionResult.PASS
    }

    companion object {
        private const val ENTITY_KEY: String = "storedEntity"

        fun writeEntityToNet(entity: Entity, user: Player, hand: InteractionHand) {
            val output = TagValueOutput.createWithoutContext(ProblemReporter.DISCARDING)
            entity.save(output)
            val entityCompound = output.buildResult()
            entityCompound.putBoolean("PersistenceRequired", true)
            entityCompound.putBoolean("FromFishingNet", true)
            val itemStack = user.getItemInHand(hand)
            itemStack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY) { data ->
                data.update { compoundTag -> compoundTag.put(ENTITY_KEY, entityCompound) }
            }
        }

        fun getEntityFromNBT(customData: CustomData): Optional<EntityType<*>> {
            val nbt = customData.copyTag()
            val storedNBT = nbt.getCompoundOrEmpty(ENTITY_KEY)
            return storedNBT.getString("id").flatMap { EntityType.byString(it) }
        }

        fun alreadyHasFish(stack: ItemStack): Boolean {
            val customData = stack.components.get(DataComponents.CUSTOM_DATA)
            val stored = customData?.copyTag()?.getCompoundOrEmpty(ENTITY_KEY) ?: return false
            return stored.contains("id")
        }
    }
}