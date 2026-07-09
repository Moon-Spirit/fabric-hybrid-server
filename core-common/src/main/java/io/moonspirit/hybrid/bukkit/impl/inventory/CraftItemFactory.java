package io.moonspirit.hybrid.bukkit.impl.inventory;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftItemFactory implements ItemFactory {

    private static final CraftItemFactory INSTANCE = new CraftItemFactory();

    public static CraftItemFactory instance() {
        return INSTANCE;
    }

    @Override
    public ItemMeta getItemMeta(Material material) {
        if (material == null || !material.isItem()) return null;
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNewCraftStack(material).getHandle();
        return new CraftItemMeta(nmsStack);
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        if (meta == null || stack == null) return false;
        return true;
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material material) throws IllegalArgumentException {
        if (meta == null || material == null) return false;
        if (!material.isItem()) return false;
        return true;
    }

    @Override
    public boolean equals(ItemMeta meta1, ItemMeta meta2) throws IllegalArgumentException {
        if (meta1 == meta2) return true;
        if (meta1 == null || meta2 == null) return false;
        if (!(meta1 instanceof CraftItemMeta) || !(meta2 instanceof CraftItemMeta)) return false;
        return meta1.equals(meta2);
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        if (meta == null) return null;
        if (stack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        return meta;
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, Material material) throws IllegalArgumentException {
        if (meta == null) return null;
        if (!(meta instanceof CraftItemMeta craftMeta)) return meta;
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNewCraftStack(material).getHandle();
        return new CraftItemMeta(nmsStack);
    }

    @Override
    public Color getDefaultLeatherColor() {
        return Color.fromRGB(10511680);
    }

    @Override
    public Material getSpawnEgg(EntityType type) {
        return Material.AIR;
    }

    @Override
    public Material updateMaterial(ItemMeta meta, Material material) throws IllegalArgumentException {
        return material;
    }

    @Override
    public ItemStack createItemStack(String data) throws IllegalArgumentException {
        return CraftItemStack.asNewCraftStack(Material.AIR);
    }
}
