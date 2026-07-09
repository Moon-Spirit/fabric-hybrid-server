package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;

public class CraftBlaze extends CraftMob implements Blaze {

    protected final net.minecraft.world.entity.monster.Blaze blazeHandle;

    public CraftBlaze(net.minecraft.world.entity.monster.Blaze handle) {
        super(handle);
        this.blazeHandle = handle;
    }

    public net.minecraft.world.entity.monster.Blaze getBlazeHandle() {
        return blazeHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.BLAZE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftBlaze other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftBlaze{id=" + getEntityId() + "}";
    }
}
