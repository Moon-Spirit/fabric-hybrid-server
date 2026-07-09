package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;

public class CraftIronGolem extends CraftMob implements IronGolem {

    protected final net.minecraft.world.entity.animal.IronGolem ironGolemHandle;

    public CraftIronGolem(net.minecraft.world.entity.animal.IronGolem handle) {
        super(handle);
        this.ironGolemHandle = handle;
    }

    public net.minecraft.world.entity.animal.IronGolem getIronGolemHandle() {
        return ironGolemHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.IRON_GOLEM;
    }

    @Override
    public boolean isPlayerCreated() { return false; }
    @Override
    public void setPlayerCreated(boolean playerCreated) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftIronGolem other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftIronGolem{id=" + getEntityId() + "}";
    }
}
