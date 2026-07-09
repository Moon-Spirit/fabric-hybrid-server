package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimap;

import java.util.*;
import java.util.stream.Collectors;

public class CraftItemMeta implements ItemMeta {

    private final net.minecraft.world.item.ItemStack itemStack;

    public CraftItemMeta(net.minecraft.world.item.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public CompoundTag getTag() {
        return itemStack.hasTag() ? itemStack.getTag() : null;
    }

    private CompoundTag getOrCreateTag() {
        return itemStack.getOrCreateTag();
    }

    private CompoundTag getOrCreateDisplayTag() {
        CompoundTag tag = getOrCreateTag();
        if (!tag.contains("display", Tag.TAG_COMPOUND)) {
            tag.put("display", new CompoundTag());
        }
        return tag.getCompound("display");
    }

    // ===== Display Name =====

    @Override
    public String getDisplayName() {
        if (!itemStack.hasTag()) return null;
        CompoundTag tag = itemStack.getTag();
        if (tag.contains("display", Tag.TAG_COMPOUND)) {
            CompoundTag display = tag.getCompound("display");
            if (display.contains("Name", Tag.TAG_STRING)) {
                String json = display.getString("Name");
                try {
                    Component component = Component.Serializer.fromJson(json);
                    return component != null ? component.getString() : null;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public void setDisplayName(String name) {
        if (name == null || name.isEmpty()) {
            if (itemStack.hasTag()) {
                CompoundTag tag = itemStack.getTag();
                if (tag.contains("display", Tag.TAG_COMPOUND)) {
                    CompoundTag display = tag.getCompound("display");
                    display.remove("Name");
                    if (display.isEmpty()) {
                        tag.remove("display");
                    }
                }
            }
            return;
        }
        Component component = Component.literal(name);
        String json = Component.Serializer.toJson(component);
        getOrCreateDisplayTag().putString("Name", json);
    }

    @Override
    public boolean hasDisplayName() {
        if (!itemStack.hasTag()) return false;
        CompoundTag tag = itemStack.getTag();
        if (tag.contains("display", Tag.TAG_COMPOUND)) {
            return tag.getCompound("display").contains("Name", Tag.TAG_STRING);
        }
        return false;
    }

    // ===== Lore =====

    @Override
    public List<String> getLore() {
        if (!itemStack.hasTag()) return null;
        CompoundTag tag = itemStack.getTag();
        if (tag.contains("display", Tag.TAG_COMPOUND)) {
            CompoundTag display = tag.getCompound("display");
            if (display.contains("Lore", Tag.TAG_LIST)) {
                ListTag list = display.getList("Lore", Tag.TAG_STRING);
                List<String> lore = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    String json = list.getString(i);
                    try {
                        Component component = Component.Serializer.fromJson(json);
                        lore.add(component != null ? component.getString() : json);
                    } catch (Exception e) {
                        lore.add(json);
                    }
                }
                return lore;
            }
        }
        return null;
    }

    @Override
    public void setLore(List<String> lore) {
        if (lore == null || lore.isEmpty()) {
            if (itemStack.hasTag()) {
                CompoundTag tag = itemStack.getTag();
                if (tag.contains("display", Tag.TAG_COMPOUND)) {
                    CompoundTag display = tag.getCompound("display");
                    display.remove("Lore");
                    if (display.isEmpty()) {
                        tag.remove("display");
                    }
                }
            }
            return;
        }
        ListTag list = new ListTag();
        for (String line : lore) {
            if (line == null) continue;
            Component component = Component.literal(line);
            String json = Component.Serializer.toJson(component);
            list.add(StringTag.valueOf(json));
        }
        getOrCreateDisplayTag().put("Lore", list);
    }

    @Override
    public boolean hasLore() {
        if (!itemStack.hasTag()) return false;
        CompoundTag tag = itemStack.getTag();
        if (tag.contains("display", Tag.TAG_COMPOUND)) {
            CompoundTag display = tag.getCompound("display");
            return display.contains("Lore", Tag.TAG_LIST) && !display.getList("Lore", Tag.TAG_STRING).isEmpty();
        }
        return false;
    }

    // ===== Enchantments =====

    @Override
    public boolean hasEnchants() {
        if (!itemStack.hasTag()) return false;
        CompoundTag tag = itemStack.getTag();
        return tag.contains("Enchantments", Tag.TAG_LIST) && !tag.getList("Enchantments", Tag.TAG_COMPOUND).isEmpty();
    }

    @Override
    public boolean hasEnchant(Enchantment ench) {
        return getEnchantLevel(ench) > 0;
    }

    @Override
    public int getEnchantLevel(Enchantment ench) {
        if (ench == null || !itemStack.hasTag()) return 0;
        CompoundTag tag = itemStack.getTag();
        if (!tag.contains("Enchantments", Tag.TAG_LIST)) return 0;
        ListTag list = tag.getList("Enchantments", Tag.TAG_COMPOUND);
        ResourceLocation enchKey = new ResourceLocation(ench.getKey().toString());
        for (int i = 0; i < list.size(); i++) {
            CompoundTag enchTag = list.getCompound(i);
            String id = enchTag.getString("id");
            if (id.equals(enchKey.toString())) {
                return enchTag.getInt("lvl");
            }
        }
        return 0;
    }

    @Override
    public boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
        if (ench == null) return false;
        removeEnchant(ench);
        CompoundTag tag = getOrCreateTag();
        ListTag list;
        if (tag.contains("Enchantments", Tag.TAG_LIST)) {
            list = tag.getList("Enchantments", Tag.TAG_COMPOUND);
        } else {
            list = new ListTag();
            tag.put("Enchantments", list);
        }
        CompoundTag enchTag = new CompoundTag();
        enchTag.putString("id", ench.getKey().toString());
        enchTag.putInt("lvl", level);
        list.add(enchTag);
        return true;
    }

    @Override
    public boolean removeEnchant(Enchantment ench) {
        if (ench == null || !itemStack.hasTag()) return false;
        CompoundTag tag = itemStack.getTag();
        if (!tag.contains("Enchantments", Tag.TAG_LIST)) return false;
        ListTag list = tag.getList("Enchantments", Tag.TAG_COMPOUND);
        ResourceLocation enchKey = new ResourceLocation(ench.getKey().toString());
        for (int i = 0; i < list.size(); i++) {
            CompoundTag enchTag = list.getCompound(i);
            if (enchTag.getString("id").equals(enchKey.toString())) {
                list.remove(i);
                if (list.isEmpty()) {
                    tag.remove("Enchantments");
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        Map<Enchantment, Integer> enchants = new HashMap<>();
        if (!itemStack.hasTag()) return enchants;
        CompoundTag tag = itemStack.getTag();
        if (!tag.contains("Enchantments", Tag.TAG_LIST)) return enchants;
        ListTag list = tag.getList("Enchantments", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag enchTag = list.getCompound(i);
            String id = enchTag.getString("id");
            int level = enchTag.getInt("lvl");
            Enchantment ench = Enchantment.getByKey(NamespacedKey.fromString(id));
            if (ench != null) {
                enchants.put(ench, level);
            }
        }
        return enchants;
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment ench) {
        return hasEnchant(ench);
    }

    // ===== Attribute Modifiers =====

    @Override
    public boolean hasAttributeModifiers() {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return null;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return null;
    }

    @Override
    public Collection<AttributeModifier> getAttributeModifiers(Attribute attribute) {
        return null;
    }

    @Override
    public boolean addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        return false;
    }

    @Override
    public void setAttributeModifiers(Multimap<Attribute, AttributeModifier> attributeModifiers) {
    }

    @Override
    public boolean removeAttributeModifier(Attribute attribute) {
        return false;
    }

    @Override
    public boolean removeAttributeModifier(EquipmentSlot slot) {
        return false;
    }

    @Override
    public boolean removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        return false;
    }

    // ===== Stored Enchantments (for enchanted books) =====

    @Override
    public void addItemFlags(org.bukkit.inventory.ItemFlag... itemFlags) {
    }

    @Override
    public void removeItemFlags(org.bukkit.inventory.ItemFlag... itemFlags) {
    }

    @Override
    public Set<org.bukkit.inventory.ItemFlag> getItemFlags() {
        return Collections.emptySet();
    }

    @Override
    public boolean hasItemFlag(org.bukkit.inventory.ItemFlag itemFlag) {
        return false;
    }

    // ===== Unbreakable =====

    @Override
    public boolean isUnbreakable() {
        return false;
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
    }

    // ===== Localized Name =====

    @Override
    public boolean hasLocalizedName() {
        return false;
    }

    @Override
    public String getLocalizedName() {
        return null;
    }

    @Override
    public void setLocalizedName(String name) {
    }

    // ===== Custom Model Data =====

    @Override
    public boolean hasCustomModelData() {
        return false;
    }

    @Override
    public int getCustomModelData() {
        return 0;
    }

    @Override
    public void setCustomModelData(Integer data) {
    }

    // ===== Persistent Data =====

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    // ===== Custom Tag Container (deprecated) =====

    @Override
    @Deprecated
    public CustomItemTagContainer getCustomTagContainer() {
        return null;
    }

    // ===== Version =====

    @Override
    public void setVersion(int version) {
    }

    public int getVersion() {
        return 0;
    }

    // ===== Clone =====

    @Override
    public CraftItemMeta clone() {
        CraftItemMeta meta = new CraftItemMeta(itemStack);
        return meta;
    }

    // ===== Serialization =====

    @Override
    public Map<String, Object> serialize() {
        return Collections.emptyMap();
    }

    @Override
    public String getAsString() {
        return getTag() != null ? getTag().toString() : "{}";
    }

    // ===== Equals & HashCode =====

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftItemMeta other)) return false;
        CompoundTag tag = getTag();
        CompoundTag otherTag = other.getTag();
        if (tag == null && otherTag == null) return true;
        if (tag == null || otherTag == null) return false;
        return tag.equals(otherTag);
    }

    @Override
    public int hashCode() {
        CompoundTag tag = getTag();
        return tag != null ? tag.hashCode() : 0;
    }
}
