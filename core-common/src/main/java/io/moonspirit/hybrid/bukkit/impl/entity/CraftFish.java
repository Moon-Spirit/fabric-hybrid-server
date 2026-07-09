package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;

public class CraftFish extends CraftMob implements Fish {

    protected final net.minecraft.world.entity.animal.AbstractFish fishHandle;

    public CraftFish(net.minecraft.world.entity.animal.AbstractFish handle) {
        super(handle);
        this.fishHandle = handle;
    }

    public net.minecraft.world.entity.animal.AbstractFish getFishHandle() {
        return fishHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.COD;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftFish other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftFish{id=" + getEntityId() + "}";
    }
}
