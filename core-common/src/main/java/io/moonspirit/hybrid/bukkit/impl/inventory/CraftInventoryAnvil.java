package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.inventory.AnvilInventory;

public class CraftInventoryAnvil extends CraftInventory implements AnvilInventory {

    public CraftInventoryAnvil(Container container) {
        super(container);
    }

    @Override
    public String getRenameText() { return null; }

    @Override
    public int getRepairCostAmount() { return 0; }

    @Override
    public void setRepairCostAmount(int amount) {}

    @Override
    public int getRepairCost() { return 0; }

    @Override
    public void setRepairCost(int cost) {}

    @Override
    public int getMaximumRepairCost() { return 40; }

    @Override
    public void setMaximumRepairCost(int cost) {}
}
