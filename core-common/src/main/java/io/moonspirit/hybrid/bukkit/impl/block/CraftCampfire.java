package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.Campfire;
import org.bukkit.inventory.ItemStack;

public class CraftCampfire extends CraftBlockState implements Campfire {

    public CraftCampfire(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public int getSize() { return 0; }

    @Override
    public ItemStack getItem(int index) { return null; }

    @Override
    public void setItem(int index, ItemStack item) {}

    @Override
    public int getCookTime(int index) { return 0; }

    @Override
    public void setCookTime(int index, int cookTime) {}

    @Override
    public int getCookTimeTotal(int index) { return 0; }

    @Override
    public void setCookTimeTotal(int index, int cookTimeTotal) {}
}
