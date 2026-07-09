package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.Map;

public class CraftFurnace extends CraftBlockState implements Furnace {

    public CraftFurnace(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public short getBurnTime() {
        return 0;
    }

    @Override
    public void setBurnTime(short burnTime) {
    }

    @Override
    public short getCookTime() {
        return 0;
    }

    @Override
    public void setCookTime(short cookTime) {
    }

    @Override
    public int getCookTimeTotal() {
        return 0;
    }

    @Override
    public void setCookTimeTotal(int cookTimeTotal) {
    }

    @Override
    public Map<CookingRecipe<?>, Integer> getRecipesUsed() {
        return Collections.emptyMap();
    }

    @Override
    public FurnaceInventory getInventory() {
        return null;
    }

    @Override
    public FurnaceInventory getSnapshotInventory() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public void setLock(String key) {
    }

    @Override
    public String getLock() {
        return null;
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

}
