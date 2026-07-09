package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

public class CraftCreatureSpawner extends CraftBlockState implements CreatureSpawner {

    public CraftCreatureSpawner(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public EntityType getSpawnedType() { return EntityType.PIG; }

    @Override
    public void setSpawnedType(EntityType type) {}

    @Override
    public void setCreatureTypeByName(String name) {}

    @Override
    public String getCreatureTypeName() { return ""; }

    @Override
    public int getDelay() { return 0; }

    @Override
    public void setDelay(int delay) {}

    @Override
    public int getMinSpawnDelay() { return 0; }

    @Override
    public void setMinSpawnDelay(int delay) {}

    @Override
    public int getMaxSpawnDelay() { return 0; }

    @Override
    public void setMaxSpawnDelay(int delay) {}

    @Override
    public int getSpawnCount() { return 0; }

    @Override
    public void setSpawnCount(int count) {}

    @Override
    public int getMaxNearbyEntities() { return 0; }

    @Override
    public void setMaxNearbyEntities(int max) {}

    @Override
    public int getRequiredPlayerRange() { return 0; }

    @Override
    public void setRequiredPlayerRange(int range) {}

    @Override
    public int getSpawnRange() { return 0; }

    @Override
    public void setSpawnRange(int range) {}
}
