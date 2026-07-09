package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Location;
import org.bukkit.entity.Allay;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;

public class CraftAllay extends CraftMob implements Allay {

    protected final net.minecraft.world.entity.animal.allay.Allay allayHandle;

    public CraftAllay(net.minecraft.world.entity.animal.allay.Allay handle) {
        super(handle);
        this.allayHandle = handle;
    }

    public net.minecraft.world.entity.animal.allay.Allay getAllayHandle() {
        return allayHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.ALLAY;
    }

    @Override
    public boolean canDuplicate() { return false; }
    @Override
    public void setCanDuplicate(boolean canDuplicate) {}
    @Override
    public long getDuplicationCooldown() { return 0; }
    @Override
    public void setDuplicationCooldown(long cooldown) {}
    @Override
    public void resetDuplicationCooldown() {}
    @Override
    public boolean isDancing() { return false; }
    @Override
    public void startDancing(Location location) {}
    @Override
    public void startDancing() {}
    @Override
    public void stopDancing() {}
    @Override
    public Allay duplicateAllay() { return null; }
    @Override
    public Location getJukebox() { return null; }

    @Override
    public Inventory getInventory() { return null; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftAllay other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftAllay{id=" + getEntityId() + "}";
    }
}
