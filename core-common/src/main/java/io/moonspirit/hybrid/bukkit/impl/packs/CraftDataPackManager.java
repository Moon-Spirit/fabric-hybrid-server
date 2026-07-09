package io.moonspirit.hybrid.bukkit.impl.packs;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.packs.DataPack;
import org.bukkit.packs.DataPackManager;

import java.util.Collection;
import java.util.Collections;

public class CraftDataPackManager implements DataPackManager {

    @Override
    public Collection<DataPack> getDataPacks() {
        return Collections.emptyList();
    }

    @Override
    public DataPack getDataPack(NamespacedKey key) {
        return null;
    }

    @Override
    public Collection<DataPack> getEnabledDataPacks(World world) {
        return Collections.emptyList();
    }

    @Override
    public Collection<DataPack> getDisabledDataPacks(World world) {
        return Collections.emptyList();
    }

    @Override
    public boolean isEnabledByFeature(org.bukkit.Material material, World world) {
        return false;
    }

    @Override
    public boolean isEnabledByFeature(EntityType entityType, World world) {
        return false;
    }
}
