package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider<Biome>(output, Registries.BIOME, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {

        //#region Vanilla Tags
        builder(BiomeTags.HAS_RUINED_PORTAL_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "seagrass_bed")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "red_meadow")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lukewarm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "warm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "frozen_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_coral_reef")))

        builder(BiomeTags.IS_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "seagrass_bed")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "red_meadow")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))

        builder(BiomeTags.IS_DEEP_OCEAN)
            .addTag(HAPIBiomeTags.ALL_TRENCHES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))

        builder(HAPIBiomeTags.IS_DEEP_OCEAN)
            .addTag(HAPIBiomeTags.ALL_TRENCHES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))

        builder(BiomeTags.IS_BEACH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))

        builder(HAPIBiomeTags.IS_SANDY)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))

        builder(HAPIBiomeTags.IS_WET)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))

        builder(HAPIBiomeTags.IS_HOT)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "seagrass_bed")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "red_meadow")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "warm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lukewarm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "sulfuric_caves")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lush_desert")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_estuary")))

        builder(HAPIBiomeTags.IS_COLD)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "frozen_trench")))

        builder(HAPIBiomeTags.IS_SHALLOW_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "seagrass_bed")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "red_meadow")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))

        builder(HAPIBiomeTags.IS_CAVE)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "sulfuric_caves")))

        builder(HAPIBiomeTags.IS_AQUATIC)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "sulfuric_caves")))

        builder(HAPIBiomeTags.IS_TEMPERATE)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "trench")))

        builder(HAPIBiomeTags.IS_OVERWORLD)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "seagrass_bed")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "red_meadow")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "sulfuric_caves")))

            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "blackwater_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "forested_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "floral_river")))

            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_estuary")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "blackwater_estuary")))

            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lush_desert")))

            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "frozen_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lukewarm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "warm_trench")))

        builder(HAPIBiomeTags.HAPI_DEEP_OCEANS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))
            .addOptional(Biomes.DEEP_OCEAN)
            .addOptional(Biomes.DEEP_COLD_OCEAN)
            .addOptional(Biomes.DEEP_FROZEN_OCEAN)
            .addOptional(Biomes.DEEP_LUKEWARM_OCEAN)

        builder(HAPIBiomeTags.CAN_SUMMON_SHELL_BEAST)
            .addOptionalTag(HAPIBiomeTags.HAPI_DEEP_OCEANS)
            .addOptionalTag(HAPIBiomeTags.ALL_TRENCHES)
            .addOptionalTag(BiomeTags.IS_DEEP_OCEAN)

        builder(BiomeTags.IS_RIVER)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "blackwater_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "forested_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "floral_river")))
        //#endregion

        //#region Arctic Ocean Tags
        builder(HAPIBiomeTags.FROZEN_OCEANS)
            .add(
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN
            )
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arctic_deep_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arctic_shallow_ocean")))

        builder(HAPIBiomeTags.SHALLOW_FROZEN_OCEANS)
            .add(Biomes.FROZEN_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arctic_shallow_ocean")))

        builder(HAPIBiomeTags.DEEP_FROZEN_OCEANS)
            .add(Biomes.DEEP_FROZEN_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arctic_deep_ocean")))

        builder(HAPIBiomeTags.FROZEN_TRENCH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "frozen_trench")))
        //#endregion

        //#region Cold Ocean Tags
        builder(HAPIBiomeTags.COLD_OCEANS)
            .add(
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN
            )
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "cold_shallow_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "cold_deep_ocean")))

        builder(HAPIBiomeTags.SHALLOW_COLD_OCEANS)
            .add(Biomes.COLD_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "cold_shallow_ocean")))

        builder(HAPIBiomeTags.DEEP_COLD_OCEANS)
            .add(Biomes.DEEP_COLD_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "cold_deep_ocean")))

        builder(HAPIBiomeTags.COLD_TRENCH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_trench")))
        //#endregion

        //#region Temperate Ocean Tags
        builder(HAPIBiomeTags.TEMPERATE_OCEANS)
            .add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN
            )
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_shallow_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_deep_ocean")))

        builder(HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS)
            .add(Biomes.OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_shallow_ocean")))

        builder(HAPIBiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(Biomes.DEEP_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_deep_ocean")))

        builder(HAPIBiomeTags.TEMPERATE_TRENCH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "trench")))
        //#endregion

        //#region Lukewarm Ocean Tags
        builder(HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS)
            .add(Biomes.LUKEWARM_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "subtropical_shallow_ocean")))

        builder(HAPIBiomeTags.LUKEWARM_OCEANS)
            .add(
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN
            )
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "subtropical_shallow_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "subtropical_deep_ocean")))

        builder(HAPIBiomeTags.DEEP_LUKEWARM_OCEANS)
            .add(Biomes.DEEP_LUKEWARM_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "subtropical_deep_ocean")))

        builder(HAPIBiomeTags.LUKEWARM_TRENCH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lukewarm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "warm_trench")))
        //#endregion

        //#region Warm Ocean Tags
        builder(HAPIBiomeTags.SHALLOW_WARM_OCEANS)
            .add(Biomes.WARM_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tropical_shallow_ocean")))

        builder(HAPIBiomeTags.WARM_OCEANS)
            .add(Biomes.WARM_OCEAN)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tropical_deep_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tropical_shallow_ocean")))

        builder(HAPIBiomeTags.DEEP_WARM_OCEANS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_warm_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("spawn", "deep_warm_ocean")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tropical_deep_ocean")))

        builder(HAPIBiomeTags.WARM_TRENCH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "warm_trench")))

        builder(HAPIBiomeTags.CORAL_REEF)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "rocky_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "lush_stacks")))

        builder(HAPIBiomeTags.SEAGRASS_BED)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "seagrass_bed")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("spawn", "seagrass_meadow")))

        builder(HAPIBiomeTags.RED_MEADOW)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "red_meadow")))
        //#endregion

        //#region Misc Deep Sea Tags
        builder(HAPIBiomeTags.ALL_TRENCHES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "frozen_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lukewarm_trench")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "warm_trench")))

        builder(HAPIBiomeTags.DEEP_REEF)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_deep_coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("spawn", "deep_warm_ocean")))

        builder(HAPIBiomeTags.HAS_WHALE_FALL)
            .addTag(HAPIBiomeTags.ALL_TRENCHES)
            .addOptionalTag(BiomeTags.IS_DEEP_OCEAN)

        builder(HAPIBiomeTags.HAS_THERMAL_VENTS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "sulfuric_caves")))

        builder(HAPIBiomeTags.SULFURIC_CAVE)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "sulfuric_caves")))
        //#endregion

        //#region Beach Tags
        builder(HAPIBiomeTags.SANDY_BEACHES)
            .add(Biomes.BEACH)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("wythers", "tropical_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "dune_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "rainbow_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terrestria", "volcanic_island_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("mysticsbiomes", "lagoon")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "mediterranean_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arid_beach")))

        builder(HAPIBiomeTags.ROCKY_BEACHES)
            .add(Biomes.STONY_SHORE)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "gravel_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "dacite_shore")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "basalt_barrera")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "taiga_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tundra_beach")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arctic_beach")))

        builder(HAPIBiomeTags.TIDE_POOLS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))
        //#endregion

        //#region River Tags
        builder(HAPIBiomeTags.RIVERS)
            .add(Biomes.RIVER)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "muddy_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("riverredux", "sandy_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("riverredux", "carved_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "warm_temperate_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "mediterranean_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "forested_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "blackwater_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "floral_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_river")))

        builder(HAPIBiomeTags.TROPICAL_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("wythers", "jungle_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("wythers", "tropical_forest_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terralith", "warm_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "tropical_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("riverredux", "tropical_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "steppe_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tropical_rainforest_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))

        builder(HAPIBiomeTags.PLAINS_RIVERS)
            .add(Biomes.RIVER)

        builder(HAPIBiomeTags.FORESTED_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "forested_river")))

        builder(HAPIBiomeTags.FLORAL_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "floral_river")))

        builder(HAPIBiomeTags.BLACKWATER_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "blackwater_river")))

        builder(HAPIBiomeTags.EXOTIC_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_river")))

        builder(HAPIBiomeTags.SAVANNA_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_river")))

        builder(HAPIBiomeTags.DESERT_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_desert_river")))

        builder(HAPIBiomeTags.BADLANDS_RIVERS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "exotic_badlands_river")))

        builder(HAPIBiomeTags.COLD_RIVERS)
            .add(Biomes.FROZEN_RIVER)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "cold_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("riverredux", "gravelly_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "cold_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "arctic_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "tundra_river")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "boreal_river")))
        //#endregion

        //#region Misc Biome Tags
        builder(HAPIBiomeTags.LUSH_DESERT)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "lush_desert")))

        builder(HAPIBiomeTags.JUNGLE)
            .addOptionalTag(BiomeTags.IS_JUNGLE)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "eucalyptus_forest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "sparse_rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("wythers", "flooded_jungle")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "rocky_rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "crag_gardens")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "jacaranda_jungle")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "fragment_jungle")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "tropical_rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terrestria", "hemlock_rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terrestria", "hemlock_clearing")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terrestria", "rainbow_rainforest")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terrestria", "rainbow_rainforest_lake")))

        builder(HAPIBiomeTags.CHERRY)
            .add(Biomes.CHERRY_GROVE)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "mauve_hills")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "magnolia_woodland")))

        builder(HAPIBiomeTags.CAVES)
            .add(Biomes.LUSH_CAVES)
            .add(Biomes.DRIPSTONE_CAVES)
            .add(Biomes.DEEP_DARK)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "redstone_caves")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "bioshroom_caves")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "scorching_caves")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "ancient_delta")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "prismachasm")))

        builder(HAPIBiomeTags.SWAMP)
            .add(Biomes.SWAMP)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "bayou")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("wythers", "waterlily_swamp")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terralith", "orchid_swamp")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "bayou")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "cypress_swamplands")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "bayou")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("terrestria", "cypress_swamp")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_swamp")))

        builder(HAPIBiomeTags.MARSHES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("regions_unexplored", "marsh")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "marsh")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "wetland")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomesoplenty", "floodplain")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "bog")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "temperate_marsh")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "fen")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "mire")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "flooded_grasslands")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "mediterranean_marsh")))

        builder(HAPIBiomeTags.MANGROVES)
            .add(Biomes.MANGROVE_SWAMP)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("biomeswevegone", "white_mangrove_marshes")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("still_life", "mangrove_marsh")))
        //#endregion

        //#region Misc Tags
        builder(HAPIBiomeTags.BOTTLE_SPAWN_BIOMES)
            .addOptionalTag(BiomeTags.IS_OCEAN)
            .addOptionalTag(BiomeTags.IS_BEACH)
        //#endregion

        //#region Compatibility Tags
            // rainbow reef
        builder(HAPIBiomeTags.RR_WARM_OCEANS)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))

            // fintastic
        builder(HAPIBiomeTags.MOONY_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_reef")))
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tide_pools")))

        builder(HAPIBiomeTags.PLECO_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))

        builder(HAPIBiomeTags.ARAPAIMA_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))

        builder(HAPIBiomeTags.CATFISH_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))

        builder(HAPIBiomeTags.GUPPY_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))

        builder(HAPIBiomeTags.MINNOW_SURFACE_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))

        builder(HAPIBiomeTags.FWSHARK_BIOMES)
            .addOptional(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("hybrid_aquatic", "tropical_river")))
        //#endregion
    }
}
