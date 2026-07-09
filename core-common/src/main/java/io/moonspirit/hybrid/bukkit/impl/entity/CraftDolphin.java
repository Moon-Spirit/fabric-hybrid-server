package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;

public class CraftDolphin extends CraftMob implements Dolphin {

    protected final net.minecraft.world.entity.animal.Dolphin dolphinHandle;

    public CraftDolphin(net.minecraft.world.entity.animal.Dolphin handle) {
        super(handle);
        this.dolphinHandle = handle;
    }

    public net.minecraft.world.entity.animal.Dolphin getDolphinHandle() {
        return dolphinHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.DOLPHIN;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftDolphin other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftDolphin{id=" + getEntityId() + "}";
    }
}
