package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.entity.player.Inventory;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CraftInventoryPlayer extends CraftInventory implements PlayerInventory {

    private final Inventory playerInventory;

    public CraftInventoryPlayer(Inventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    @Override
    public HumanEntity getHolder() {
        return null;
    }

    // ===== Armor =====

    @Override
    public ItemStack getHelmet() {
        return getItem(39);
    }

    @Override
    public ItemStack getChestplate() {
        return getItem(38);
    }

    @Override
    public ItemStack getLeggings() {
        return getItem(37);
    }

    @Override
    public ItemStack getBoots() {
        return getItem(36);
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        setItem(39, helmet);
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        setItem(38, chestplate);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        setItem(37, leggings);
    }

    @Override
    public void setBoots(ItemStack boots) {
        setItem(36, boots);
    }

    // ===== Armor contents =====

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] armor = new ItemStack[4];
        armor[0] = getBoots();
        armor[1] = getLeggings();
        armor[2] = getChestplate();
        armor[3] = getHelmet();
        return armor;
    }

    @Override
    public void setArmorContents(ItemStack[] items) {
        if (items == null) return;
        if (items.length > 0) setBoots(items[0]);
        if (items.length > 1) setLeggings(items[1]);
        if (items.length > 2) setChestplate(items[2]);
        if (items.length > 3) setHelmet(items[3]);
    }

    // ===== Main hand =====

    @Override
    public ItemStack getItemInMainHand() {
        return getItem(playerInventory.selected);
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        setItem(playerInventory.selected, item);
    }

    // ===== Off hand =====

    @Override
    public ItemStack getItemInOffHand() {
        return getItem(40);
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
        setItem(40, item);
    }

    // ===== Held item slot =====

    @Override
    public int getHeldItemSlot() {
        return playerInventory.selected;
    }

    @Override
    public void setHeldItemSlot(int slot) {
        if (slot >= 0 && slot < 9) {
            playerInventory.selected = slot;
        }
    }

    // ===== EquipmentSlot =====

    @Override
    public void setItem(EquipmentSlot slot, ItemStack item) {
        switch (slot) {
            case HEAD -> setHelmet(item);
            case CHEST -> setChestplate(item);
            case LEGS -> setLeggings(item);
            case FEET -> setBoots(item);
            case HAND -> setItemInMainHand(item);
            case OFF_HAND -> setItemInOffHand(item);
        }
    }

    @Override
    public ItemStack getItem(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> getHelmet();
            case CHEST -> getChestplate();
            case LEGS -> getLeggings();
            case FEET -> getBoots();
            case HAND -> getItemInMainHand();
            case OFF_HAND -> getItemInOffHand();
        };
    }

    // ===== Deprecated =====

    @Override
    @Deprecated
    public ItemStack getItemInHand() {
        return getItemInMainHand();
    }

    @Override
    @Deprecated
    public void setItemInHand(ItemStack stack) {
        setItemInMainHand(stack);
    }

    // ===== Extra contents =====

    @Override
    public ItemStack[] getExtraContents() {
        return new ItemStack[]{getItemInOffHand()};
    }

    @Override
    public void setExtraContents(ItemStack[] items) {
        if (items != null && items.length > 0) {
            setItemInOffHand(items[0]);
        } else {
            setItemInOffHand(null);
        }
    }

    // ===== Storage contents =====

    @Override
    public ItemStack[] getStorageContents() {
        ItemStack[] contents = new ItemStack[36];
        for (int i = 0; i < 36; i++) {
            contents[i] = getItem(i);
        }
        return contents;
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        for (int i = 0; i < Math.min(items.length, 36); i++) {
            setItem(i, items[i]);
        }
        for (int i = items.length; i < 36; i++) {
            setItem(i, null);
        }
    }

    @Override
    public String toString() {
        return "CraftInventoryPlayer{size=" + getSize() + "}";
    }
}
