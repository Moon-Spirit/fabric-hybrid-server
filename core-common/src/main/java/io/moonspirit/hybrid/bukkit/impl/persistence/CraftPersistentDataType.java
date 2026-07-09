package io.moonspirit.hybrid.bukkit.impl.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class CraftPersistentDataType<T, Z> implements PersistentDataType<T, Z> {

    private final Class<T> primitiveType;
    private final Class<Z> complexType;

    public CraftPersistentDataType(Class<T> primitiveType, Class<Z> complexType) {
        this.primitiveType = primitiveType;
        this.complexType = complexType;
    }

    @Override
    public Class<T> getPrimitiveType() {
        return primitiveType;
    }

    @Override
    public Class<Z> getComplexType() {
        return complexType;
    }

    @Override
    public T toPrimitive(Z complex, PersistentDataAdapterContext context) {
        return null;
    }

    @Override
    public Z fromPrimitive(T primitive, PersistentDataAdapterContext context) {
        return null;
    }
}
