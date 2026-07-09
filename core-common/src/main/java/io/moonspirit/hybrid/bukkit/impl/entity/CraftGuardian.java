package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;

public class CraftGuardian extends CraftMob implements Guardian {

    protected final net.minecraft.world.entity.monster.Guardian guardianHandle;

    public CraftGuardian(net.minecraft.world.entity.monster.Guardian handle) {
        super(handle);
        this.guardianHandle = handle;
    }

    public net.minecraft.world.entity.monster.Guardian getGuardianHandle() {
        return guardianHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.GUARDIAN;
    }

    @Override
    public boolean setLaser(boolean laser) { return false; }
    @Override
    public boolean hasLaser() { return false; }
    @Override
    public int getLaserDuration() { return 0; }
    @Override
    public void setLaserTicks(int ticks) {}
    @Override
    public int getLaserTicks() { return 0; }
    @Override
    public boolean isElder() { return false; }
    @Override
    public void setElder(boolean elder) {}
    @Override
    public boolean isMoving() { return false; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftGuardian other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftGuardian{id=" + getEntityId() + "}";
    }
}
