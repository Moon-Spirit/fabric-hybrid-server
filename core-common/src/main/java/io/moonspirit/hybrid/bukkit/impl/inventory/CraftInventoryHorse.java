package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryHorse extends CraftInventory implements HorseInventory {

    public CraftInventoryHorse(Container container) {
        super(container);
    }

    @Override
    public ItemStack getSaddle() { return getItem(0); }

    @Override
    public void setSaddle(ItemStack stack) { setItem(0, stack); }

    @Override
    public ItemStack getArmor() { return getItem(1); }

    @Override
    public void setArmor(ItemStack stack) { setItem(1, stack); }
}
