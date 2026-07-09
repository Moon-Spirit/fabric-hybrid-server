package io.moonspirit.hybrid.bukkit.impl.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class CraftEnchantment extends Enchantment {

    private final String name;
    private final int maxLevel;
    private final int startLevel;
    private final EnchantmentTarget target;
    private final boolean treasure;
    private final boolean cursed;

    public CraftEnchantment(NamespacedKey key, String name, int maxLevel, int startLevel,
                            EnchantmentTarget target, boolean treasure, boolean cursed) {
        super(key);
        this.name = name;
        this.maxLevel = maxLevel;
        this.startLevel = startLevel;
        this.target = target;
        this.treasure = treasure;
        this.cursed = cursed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return startLevel;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return target;
    }

    @Override
    public boolean isTreasure() {
        return treasure;
    }

    @Override
    public boolean isCursed() {
        return cursed;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }
}
