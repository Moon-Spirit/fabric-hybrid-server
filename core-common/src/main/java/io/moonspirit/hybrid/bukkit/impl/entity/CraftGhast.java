package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;

public class CraftGhast extends CraftMob implements Ghast {

    protected final net.minecraft.world.entity.monster.Ghast ghastHandle;

    public CraftGhast(net.minecraft.world.entity.monster.Ghast handle) {
        super(handle);
        this.ghastHandle = handle;
    }

    public net.minecraft.world.entity.monster.Ghast getGhastHandle() {
        return ghastHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.GHAST;
    }

    @Override
    public boolean isCharging() { return false; }
    @Override
    public void setCharging(boolean charging) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftGhast other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftGhast{id=" + getEntityId() + "}";
    }
}
