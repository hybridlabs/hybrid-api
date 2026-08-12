package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {

        getOrCreateTagBuilder(HAPIItemTags.TUNA)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "tuna"))

        getOrCreateTagBuilder(HAPIItemTags.MACKEREL)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "mackerel"))

        getOrCreateTagBuilder(HAPIItemTags.HERRING)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "herring"))

        getOrCreateTagBuilder(HAPIItemTags.PUFFERFISH)
            .add(Items.PUFFERFISH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "blowfish"))

        getOrCreateTagBuilder(HAPIItemTags.CARP)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "carp"))

        getOrCreateTagBuilder(HAPIItemTags.EEL)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "moray_eel"))

        getOrCreateTagBuilder(HAPIItemTags.RAY)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "stingray"))

        getOrCreateTagBuilder(HAPIItemTags.HAGFISH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "hagfish"))

        getOrCreateTagBuilder(HAPIItemTags.GLOWSLIME_FISH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "anglerfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "barreleye"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "dragonfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "flashlight_fish"))

        getOrCreateTagBuilder(HAPIItemTags.SHRIMP)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_shrimp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_shrimp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_crayfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_crayfish"))

        getOrCreateTagBuilder(HAPIItemTags.TENTACLE)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_tentacle"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_tentacle"))

        getOrCreateTagBuilder(HAPIItemTags.UNI)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "uni"))

        getOrCreateTagBuilder(HAPIItemTags.FISH_MEAT)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_fish_meat"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_fish_meat"))

        getOrCreateTagBuilder(HAPIItemTags.FISH_STEAK)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_fish_steak"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_fish_steak"))

        getOrCreateTagBuilder(HAPIItemTags.LOBSTER_CLAW)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_lobster"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_lobster"))

        getOrCreateTagBuilder(HAPIItemTags.LOBSTER_TAIL)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_lobster_tail"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_lobster_tail"))

        getOrCreateTagBuilder(HAPIItemTags.LOBSTER_MEAT)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_lobster"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_lobster"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_lobster_tail"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_lobster_tail"))

        getOrCreateTagBuilder(HAPIItemTags.CRAB_MEAT)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "raw_crab"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "cooked_crab"))

        getOrCreateTagBuilder(HAPIItemTags.TUBE_SPONGE)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "tube_sponge"))

        getOrCreateTagBuilder(HAPIItemTags.BULL_KELP)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "bull_kelp"))

        getOrCreateTagBuilder(HAPIItemTags.CORAL_CHUNK)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "coral_chunk"))

        getOrCreateTagBuilder(HAPIItemTags.SHARK_TOOTH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "shark_tooth"))

        getOrCreateTagBuilder(HAPIItemTags.GLOWSLIME)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "glowslime"))

        getOrCreateTagBuilder(HAPIItemTags.HAGSLIME)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "hagslime"))

        getOrCreateTagBuilder(HAPIItemTags.BRINE_BUCKET)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "brine_bucket"))
    }
}