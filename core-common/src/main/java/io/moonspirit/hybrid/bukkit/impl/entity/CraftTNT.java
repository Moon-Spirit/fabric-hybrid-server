package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;

public class CraftTNT extends CraftEntity implements TNTPrimed {

    protected final net.minecraft.world.entity.item.PrimedTnt tntHandle;

    public CraftTNT(net.minecraft.world.entity.item.PrimedTnt handle) {
        super(handle);
        this.tntHandle = handle;
    }

    public net.minecraft.world.entity.item.PrimedTnt getTntHandle() {
        return tntHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.PRIMED_TNT;
    }

    @Override
    public void setFuseTicks(int ticks) { tntHandle.setFuse(ticks); }

    @Override
    public int getFuseTicks() { return tntHandle.getFuse(); }

    @Override
    public Entity getSource() { return null; }

    @Override
    public void setSource(Entity source) {}

    @Override
    public void setYield(float yield) {}

    @Override
    public float getYield() { return 4.0f; }

    @Override
    public void setIsIncendiary(boolean isIncendiary) {}

    @Override
    public boolean isIncendiary() { return false; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftTNT other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftTNT{id=" + getEntityId() + "}";
    }
}
