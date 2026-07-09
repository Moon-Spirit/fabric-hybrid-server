package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.Smoker;

public class CraftSmoker extends CraftBlockState implements Smoker {

    public CraftSmoker(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public java.util.Map<org.bukkit.inventory.CookingRecipe<?>, Integer> getRecipesUsed() {
        return java.util.Collections.emptyMap();
    }

    @Override
    public org.bukkit.inventory.FurnaceInventory getInventory() {
        return null;
    }

    @Override
    public org.bukkit.inventory.FurnaceInventory getSnapshotInventory() {
        return null;
    }

    @Override
    public int getCookTimeTotal() {
        return 0;
    }

    @Override
    public void setCookTimeTotal(int cookTimeTotal) {
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
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
