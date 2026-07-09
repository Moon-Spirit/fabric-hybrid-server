package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.IntStream;

public class CraftInventory implements Inventory {

    protected final Container container;

    public CraftInventory(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    // ===== Size =====

    @Override
    public int getSize() {
        return container.getContainerSize();
    }

    @Override
    public int getMaxStackSize() {
        return container.getMaxStackSize();
    }

    @Override
    public void setMaxStackSize(int size) {
    }

    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }

    // ===== Contents =====

    @Override
    public ItemStack[] getContents() {
        ItemStack[] contents = new ItemStack[getSize()];
        for (int i = 0; i < contents.length; i++) {
            contents[i] = getItem(i);
        }
        return contents;
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        clear();
        for (int i = 0; i < Math.min(items.length, getSize()); i++) {
            setItem(i, items[i]);
        }
    }

    @Override
    public ItemStack[] getStorageContents() {
        return getContents();
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        setContents(items);
    }

    // ===== Item get/set =====

    @Override
    public ItemStack getItem(int index) {
        net.minecraft.world.item.ItemStack nms = container.getItem(index);
        if (nms == null || nms.isEmpty()) return null;
        return new CraftItemStack(nms.copy());
    }

    @Override
    public void setItem(int index, ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            container.setItem(index, net.minecraft.world.item.ItemStack.EMPTY);
        } else {
            net.minecraft.world.item.ItemStack nms;
            if (item instanceof CraftItemStack craftStack) {
                nms = craftStack.getHandle().copy();
            } else {
                nms = CraftItemStack.fromBukkitCopy(item).getHandle();
            }
            container.setItem(index, nms);
        }
    }

    // ===== Add =====

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> leftover = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            if (item == null || item.getType().isAir()) continue;
            item = item.clone();
            // Stack with existing items
            int maxStack = item.getMaxStackSize();
            for (int j = 0; j < getSize() && item.getAmount() > 0; j++) {
                ItemStack existing = getItem(j);
                if (existing == null || existing.getType().isAir()) continue;
                if (existing.isSimilar(item) && existing.getAmount() < maxStack) {
                    int add = Math.min(item.getAmount(), maxStack - existing.getAmount());
                    existing.setAmount(existing.getAmount() + add);
                    item.setAmount(item.getAmount() - add);
                    setItem(j, existing);
                }
            }
            // Fill empty slots
            for (int j = 0; j < getSize() && item.getAmount() > 0; j++) {
                ItemStack existing = getItem(j);
                if (existing == null || existing.getType().isAir()) {
                    int amount = Math.min(item.getAmount(), maxStack);
                    ItemStack copy = item.clone();
                    copy.setAmount(amount);
                    setItem(j, copy);
                    item.setAmount(item.getAmount() - amount);
                }
            }
            if (item.getAmount() > 0) {
                leftover.put(i, item);
            }
        }
        return leftover;
    }

    // ===== Remove =====

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> leftover = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            if (item == null || item.getType().isAir()) continue;
            int toRemove = item.getAmount();
            for (int j = 0; j < getSize() && toRemove > 0; j++) {
                ItemStack existing = getItem(j);
                if (existing == null || existing.getType().isAir()) continue;
                if (existing.isSimilar(item)) {
                    int remove = Math.min(toRemove, existing.getAmount());
                    toRemove -= remove;
                    existing.setAmount(existing.getAmount() - remove);
                    if (existing.getAmount() <= 0) {
                        setItem(j, null);
                    } else {
                        setItem(j, existing);
                    }
                }
            }
            if (toRemove > 0) {
                ItemStack remaining = item.clone();
                remaining.setAmount(toRemove);
                leftover.put(i, remaining);
            }
        }
        return leftover;
    }

    @Deprecated
    public HashMap<Integer, ItemStack> removeItemAnySlot(ItemStack... items) throws IllegalArgumentException {
        return removeItem(items);
    }

    // ===== Contains =====

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return first(material) >= 0;
    }

    @Override
    public boolean contains(ItemStack item) {
        return first(item) >= 0;
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        if (amount <= 0) return true;
        int count = 0;
        for (int i = 0; i < getSize(); i++) {
            ItemStack item = getItem(i);
            if (item != null && item.getType() == material) {
                count += item.getAmount();
                if (count >= amount) return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        if (amount <= 0) return true;
        int count = 0;
        for (int i = 0; i < getSize(); i++) {
            ItemStack existing = getItem(i);
            if (existing != null && existing.isSimilar(item)) {
                count += existing.getAmount();
                if (count >= amount) return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        return contains(item, amount);
    }

    // ===== Find =====

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> slots = new HashMap<>();
        for (int i = 0; i < getSize(); i++) {
            ItemStack item = getItem(i);
            if (item != null && item.getType() == material) {
                slots.put(i, item);
            }
        }
        return slots;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        HashMap<Integer, ItemStack> slots = new HashMap<>();
        for (int i = 0; i < getSize(); i++) {
            ItemStack existing = getItem(i);
            if (existing != null && existing.isSimilar(item)) {
                slots.put(i, existing);
            }
        }
        return slots;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        for (int i = 0; i < getSize(); i++) {
            ItemStack item = getItem(i);
            if (item != null && item.getType() == material) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int first(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            ItemStack existing = getItem(i);
            if (existing != null && existing.isSimilar(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstEmpty() {
        for (int i = 0; i < getSize(); i++) {
            ItemStack item = getItem(i);
            if (item == null || item.getType().isAir()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(Material material) {
        for (int i = 0; i < getSize(); i++) {
            ItemStack item = getItem(i);
            if (item != null && item.getType() == material) {
                setItem(i, null);
            }
        }
    }

    @Override
    public void remove(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            ItemStack existing = getItem(i);
            if (existing != null && existing.isSimilar(item)) {
                setItem(i, null);
            }
        }
    }

    // ===== Clear =====

    @Override
    public void clear(int index) {
        setItem(index, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSize(); i++) {
            clear(i);
        }
    }

    // ===== Iterator =====

    @Override
    public ListIterator<ItemStack> iterator() {
        return new InventoryIterator(this);
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return new InventoryIterator(this, index);
    }

    // ===== Holder =====

    @Override
    public InventoryHolder getHolder() {
        return null;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CHEST;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    // ===== Human viewers =====

    @Override
    public List<HumanEntity> getViewers() {
        return Collections.emptyList();
    }

    @Deprecated
    public ListIterator<ItemStack> listIterator() {
        return iterator();
    }

    @Deprecated
    public ListIterator<ItemStack> listIterator(int index) {
        return iterator(index);
    }

    // ===== Close =====

    @Deprecated
    public void close() {
    }

    // ===== String representation =====

    @Override
    public String toString() {
        return "CraftInventory{type=" + getType() + ", size=" + getSize() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftInventory other)) return false;
        return container.equals(other.container);
    }

    @Override
    public int hashCode() {
        return container.hashCode();
    }

    // ===== Internal iterator =====

    private static class InventoryIterator implements ListIterator<ItemStack> {
        private final CraftInventory inventory;
        private int index;
        private int lastReturned = -1;

        InventoryIterator(CraftInventory inventory) {
            this.inventory = inventory;
            this.index = 0;
        }

        InventoryIterator(CraftInventory inventory, int index) {
            this.inventory = inventory;
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < inventory.getSize();
        }

        @Override
        public ItemStack next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = index;
            return inventory.getItem(index++);
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public ItemStack previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            lastReturned = --index;
            return inventory.getItem(index);
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (lastReturned < 0) throw new IllegalStateException();
            inventory.clear(lastReturned);
            lastReturned = -1;
        }

        @Override
        public void set(ItemStack item) {
            if (lastReturned < 0) throw new IllegalStateException();
            inventory.setItem(lastReturned, item);
        }

        @Override
        public void add(ItemStack item) {
            inventory.setItem(index++, item);
            lastReturned = -1;
        }
    }
}
