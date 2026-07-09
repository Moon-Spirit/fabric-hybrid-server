package io.moonspirit.hybrid.bukkit.impl.loot;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class CraftLootTable implements LootTable {

    @Override
    public NamespacedKey getKey() {
        return null;
    }

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        return Collections.emptyList();
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
    }
}
