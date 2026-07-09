package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.block.Lectern;
import org.bukkit.inventory.LecternInventory;

public class CraftInventoryLectern extends CraftInventory implements LecternInventory {

    public CraftInventoryLectern(Container container) {
        super(container);
    }

    @Override
    public Lectern getHolder() { return null; }
}
