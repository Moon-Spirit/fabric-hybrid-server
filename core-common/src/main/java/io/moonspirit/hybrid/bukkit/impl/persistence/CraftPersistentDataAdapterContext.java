package io.moonspirit.hybrid.bukkit.impl.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;

public class CraftPersistentDataAdapterContext implements PersistentDataAdapterContext {

    @Override
    public PersistentDataContainer newPersistentDataContainer() {
        return new CraftPersistentDataContainer();
    }
}
