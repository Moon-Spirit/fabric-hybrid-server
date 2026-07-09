package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;

public class CraftSlime extends CraftMob implements Slime {

    protected final net.minecraft.world.entity.monster.Slime slimeHandle;

    public CraftSlime(net.minecraft.world.entity.monster.Slime handle) {
        super(handle);
        this.slimeHandle = handle;
    }

    public net.minecraft.world.entity.monster.Slime getSlimeHandle() {
        return slimeHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.SLIME;
    }

    @Override
    public int getSize() { return 1; }
    @Override
    public void setSize(int size) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftSlime other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftSlime{id=" + getEntityId() + "}";
    }
}
