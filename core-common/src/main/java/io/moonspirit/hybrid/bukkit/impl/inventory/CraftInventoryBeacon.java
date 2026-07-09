package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryBeacon extends CraftInventory implements BeaconInventory {

    public CraftInventoryBeacon(Container container) {
        super(container);
    }

    @Override
    public void setItem(ItemStack stack) { setItem(0, stack); }

    @Override
    public ItemStack getItem() { return getItem(0); }
}
