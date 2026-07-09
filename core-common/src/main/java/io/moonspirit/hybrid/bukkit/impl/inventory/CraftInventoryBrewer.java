package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryBrewer extends CraftInventory implements BrewerInventory {

    public CraftInventoryBrewer(Container container) {
        super(container);
    }

    @Override
    public ItemStack getIngredient() { return getItem(3); }

    @Override
    public void setIngredient(ItemStack stack) { setItem(3, stack); }

    @Override
    public ItemStack getFuel() { return getItem(4); }

    @Override
    public void setFuel(ItemStack stack) { setItem(4, stack); }

    @Override
    public BrewingStand getHolder() { return null; }
}
