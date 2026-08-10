package dev.hybridlabs.hapi.platform.services;

import dev.hybridlabs.hapi.CommonClass;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.function.Supplier;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(@NotNull String name,
                                                                       Supplier<EntityType<T>> entityType,
                                                                       int backgroundColor, int highlightColor) {
        return CommonClass.ITEMS.register(name, () -> new SpawnEggItem(entityType.get(), backgroundColor,
                highlightColor, new Item.Properties()));
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
