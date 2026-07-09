package io.moonspirit.hybrid.bukkit.impl.loot;

import org.bukkit.NamespacedKey;
import org.bukkit.loot.LootTable;

import java.util.Collections;
import java.util.Map;

public class CraftLootTableRegistry {

    private final Map<NamespacedKey, LootTable> lootTables;

    public CraftLootTableRegistry() {
        this.lootTables = Collections.emptyMap();
    }

    public LootTable getLootTable(NamespacedKey key) {
        return lootTables.get(key);
    }

    public void registerLootTable(NamespacedKey key, LootTable lootTable) {
    }

    public Map<NamespacedKey, LootTable> getLootTables() {
        return Collections.unmodifiableMap(lootTables);
    }
}
