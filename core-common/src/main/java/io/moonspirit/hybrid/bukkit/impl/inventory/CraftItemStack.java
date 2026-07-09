package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CraftItemStack extends ItemStack {

    private net.minecraft.world.item.ItemStack handle;

    public CraftItemStack(net.minecraft.world.item.ItemStack nmsItemStack) {
        this.handle = nmsItemStack;
    }

    public net.minecraft.world.item.ItemStack getHandle() {
        return handle;
    }

    // ===== Factory methods =====

    public static CraftItemStack asCraftCopy(ItemStack original) {
        if (original == null) return null;
        if (original instanceof CraftItemStack craftStack) {
            return new CraftItemStack(craftStack.handle.copy());
        }
        return fromBukkitCopy(original);
    }

    public static CraftItemStack asNewCraftStack(Material material) {
        return asNewCraftStack(material, 1);
    }

    public static CraftItemStack asNewCraftStack(Material material, int amount) {
        if (material == null || material == Material.AIR) {
            return new CraftItemStack(net.minecraft.world.item.ItemStack.EMPTY);
        }
        Item nmsItem = BuiltInRegistries.ITEM.get(new ResourceLocation(material.getKey().toString()));
        if (nmsItem == null || nmsItem == Items.AIR) {
            return new CraftItemStack(net.minecraft.world.item.ItemStack.EMPTY);
        }
        return new CraftItemStack(new net.minecraft.world.item.ItemStack(nmsItem, amount));
    }

    public static CraftItemStack fromBukkitCopy(ItemStack original) {
        if (original == null) return null;
        if (original instanceof CraftItemStack craftStack) {
            return new CraftItemStack(craftStack.handle.copy());
        }
        CraftItemStack result = asNewCraftStack(original.getType(), original.getAmount());
        if (original.hasItemMeta()) {
            result.setItemMeta(original.getItemMeta());
        }
        return result;
    }

    // ===== Type =====

    @Override
    public Material getType() {
        if (handle == null || handle.isEmpty()) return Material.AIR;
        Item item = handle.getItem();
        if (item == Items.AIR) return Material.AIR;
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
        Material mat = Material.getMaterial(key.getPath().toUpperCase(Locale.ROOT));
        return mat != null ? mat : Material.AIR;
    }

    @Override
    public void setType(Material type) {
        if (type == null || type == Material.AIR) {
            handle = net.minecraft.world.item.ItemStack.EMPTY;
            return;
        }
        ResourceLocation key = new ResourceLocation(type.getKey().toString());
        Item item = BuiltInRegistries.ITEM.get(key);
        if (item == null || item == Items.AIR) {
            handle = net.minecraft.world.item.ItemStack.EMPTY;
            return;
        }
        int amount = Math.max(1, getAmount());
        CompoundTag oldTag = handle.hasTag() ? handle.getTag() : null;
        handle = new net.minecraft.world.item.ItemStack(item, amount);
        if (oldTag != null) {
            handle.setTag(oldTag);
        }
    }

    // ===== Amount =====

    @Override
    public int getAmount() {
        return handle != null ? handle.getCount() : 0;
    }

    @Override
    public void setAmount(int amount) {
        if (handle != null) {
            handle.setCount(amount);
        }
    }

    // ===== ItemMeta =====

    @Override
    public ItemMeta getItemMeta() {
        return new CraftItemMeta(handle);
    }

    @Override
    public boolean setItemMeta(ItemMeta itemMeta) {
        if (itemMeta == null) return false;
        if (itemMeta instanceof CraftItemMeta craftItemMeta) {
            CompoundTag tag = craftItemMeta.getTag();
            if (tag != null && !tag.isEmpty()) {
                handle.setTag(tag.copy());
            } else {
                handle.setTag(null);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hasItemMeta() {
        return handle != null && handle.hasTag() && !handle.getTag().isEmpty();
    }

    // ===== Enchantments =====

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        ItemMeta meta = getItemMeta();
        return meta != null ? meta.getEnchants() : new HashMap<>();
    }

    @Override
    public void addEnchantment(Enchantment ench, int level) {
        if (ench == null) throw new IllegalArgumentException("Enchantment cannot be null");
        ItemMeta meta = getItemMeta();
        if (meta != null) {
            meta.addEnchant(ench, level, false);
            setItemMeta(meta);
        }
    }

    @Override
    public void addUnsafeEnchantment(Enchantment ench, int level) {
        if (ench == null) throw new IllegalArgumentException("Enchantment cannot be null");
        ItemMeta meta = getItemMeta();
        if (meta != null) {
            meta.addEnchant(ench, level, true);
            setItemMeta(meta);
        }
    }

    @Override
    public int getEnchantmentLevel(Enchantment ench) {
        ItemMeta meta = getItemMeta();
        return meta != null ? meta.getEnchantLevel(ench) : 0;
    }

    @Override
    public int removeEnchantment(Enchantment ench) {
        ItemMeta meta = getItemMeta();
        if (meta != null && meta.hasEnchant(ench)) {
            int level = meta.getEnchantLevel(ench);
            meta.removeEnchant(ench);
            setItemMeta(meta);
            return level;
        }
        return 0;
    }

    @Override
    public boolean containsEnchantment(Enchantment ench) {
        ItemMeta meta = getItemMeta();
        return meta != null && meta.hasEnchant(ench);
    }

    // ===== Data (deprecated) =====

    @Override
    @Deprecated
    public org.bukkit.material.MaterialData getData() {
        return null;
    }

    @Override
    @Deprecated
    public void setData(org.bukkit.material.MaterialData data) {
    }

    // ===== Durability (deprecated) =====

    @Override
    @Deprecated
    public short getDurability() {
        return 0;
    }

    @Override
    @Deprecated
    public void setDurability(short durability) {
    }

    // ===== Max stack size =====

    @Override
    public int getMaxStackSize() {
        return handle != null ? handle.getMaxStackSize() : 64;
    }

    // ===== Comparison =====

    @Override
    public boolean isSimilar(ItemStack stack) {
        if (stack == null) return false;
        if (stack == this) return true;
        return getType() == stack.getType();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftItemStack other)) return false;
        return handle.equals(other.handle);
    }

    @Override
    public int hashCode() {
        return handle != null ? handle.hashCode() : 0;
    }

    @Override
    public CraftItemStack clone() {
        if (handle != null && !handle.isEmpty()) {
            return new CraftItemStack(handle.copy());
        }
        return new CraftItemStack(net.minecraft.world.item.ItemStack.EMPTY);
    }

    @Override
    public String toString() {
        return "CraftItemStack{type=" + getType() + ", amount=" + getAmount() + "}";
    }
}
