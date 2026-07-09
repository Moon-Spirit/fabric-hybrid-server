package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowman;

public class CraftSnowman extends CraftMob implements Snowman {

    protected final net.minecraft.world.entity.animal.SnowGolem snowmanHandle;

    public CraftSnowman(net.minecraft.world.entity.animal.SnowGolem handle) {
        super(handle);
        this.snowmanHandle = handle;
    }

    public net.minecraft.world.entity.animal.SnowGolem getSnowmanHandle() {
        return snowmanHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.SNOWMAN;
    }

    @Override
    public boolean isDerp() { return false; }
    @Override
    public void setDerp(boolean derp) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftSnowman other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftSnowman{id=" + getEntityId() + "}";
    }
}
