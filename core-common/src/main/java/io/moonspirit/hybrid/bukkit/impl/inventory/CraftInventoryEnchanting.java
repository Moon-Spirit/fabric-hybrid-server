package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryEnchanting extends CraftInventory implements EnchantingInventory {

    public CraftInventoryEnchanting(Container container) {
        super(container);
    }

    @Override
    public void setItem(ItemStack stack) { setItem(0, stack); }

    @Override
    public ItemStack getItem() { return getItem(0); }

    @Override
    public void setSecondary(ItemStack stack) { setItem(1, stack); }

    @Override
    public ItemStack getSecondary() { return getItem(1); }
}
