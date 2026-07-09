package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;

public class CraftBrewingStand extends CraftBlockState implements BrewingStand {

    public CraftBrewingStand(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public String getLock() {
        return null;
    }

    @Override
    public void setLock(String key) {
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
    }

    @Override
    public int getBrewingTime() { return 0; }

    @Override
    public void setBrewingTime(int brewingTime) {}

    @Override
    public int getFuelLevel() { return 0; }

    @Override
    public void setFuelLevel(int fuelLevel) {}

    @Override
    public BrewerInventory getInventory() { return null; }

    @Override
    public BrewerInventory getSnapshotInventory() { return null; }
}
