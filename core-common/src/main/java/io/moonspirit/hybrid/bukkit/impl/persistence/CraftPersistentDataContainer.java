package io.moonspirit.hybrid.bukkit.impl.persistence;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.Set;

public class CraftPersistentDataContainer implements PersistentDataContainer {

    @Override
    public <T, Z> void set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
    }

    @Override
    public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
        return false;
    }

    @Override
    public <T, Z> Z get(NamespacedKey key, PersistentDataType<T, Z> type) {
        return null;
    }

    @Override
    public <T, Z> Z getOrDefault(NamespacedKey key, PersistentDataType<T, Z> type, Z defaultValue) {
        return defaultValue;
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return Collections.emptySet();
    }

    @Override
    public void remove(NamespacedKey key) {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return new CraftPersistentDataAdapterContext();
    }
}
