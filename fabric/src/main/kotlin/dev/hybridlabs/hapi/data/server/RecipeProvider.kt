package dev.hybridlabs.hapi.data.server

import dev.hybridlabs.hapi.item.HAPIItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.criterion.InventoryChangeTrigger
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class RecipeProvider(output: FabricDataOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    FabricRecipeProvider(output, lookupProvider) {
    override fun getName(): String = "Creature Net"

    override fun createRecipeProvider(registries: HolderLookup.Provider, output: RecipeOutput): net.minecraft.data.recipes.RecipeProvider =
        object : net.minecraft.data.recipes.RecipeProvider(registries, output) {
            override fun buildRecipes() {
                ShapedRecipeBuilder.shaped(
                    registries.lookupOrThrow(Registries.ITEM),
                    RecipeCategory.TOOLS,
                    HAPIItems.CREATURE_NET.get()
                )
                    .pattern("  S")
                    .pattern(" IS")
                    .pattern("I  ")
                    .define('I', Items.STICK)
                    .define('S', Items.STRING)
                    .unlockedBy("string", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STRING))
                    .save(this.output)
            }
        }
}
