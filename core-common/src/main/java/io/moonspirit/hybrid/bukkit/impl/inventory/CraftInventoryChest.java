package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

public class CraftInventoryChest extends CraftInventory {

    private final InventoryHolder holder;
    private final String title;

    public CraftInventoryChest(int size) {
        this(size, null, null);
    }

    public CraftInventoryChest(int size, InventoryHolder holder) {
        this(size, holder, null);
    }

    public CraftInventoryChest(int size, InventoryHolder holder, String title) {
        super(new SimpleContainer(size));
        this.holder = holder;
        this.title = title;
    }

    public CraftInventoryChest(Container container) {
        this(container, null, null);
    }

    public CraftInventoryChest(Container container, InventoryHolder holder, String title) {
        super(container);
        this.holder = holder;
        this.title = title;
    }

    @Override
    public InventoryHolder getHolder() {
        return holder;
    }

    @Override
    public InventoryType getType() {
        int size = getSize();
        if (size == 9) return InventoryType.HOPPER;
        if (size == 27) return InventoryType.CHEST;
        if (size == 54) return InventoryType.CHEST;
        return InventoryType.CHEST;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CraftInventoryChest{title=" + title + ", size=" + getSize() + "}";
    }
}
