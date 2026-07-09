package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Raid;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pillager;
import org.bukkit.inventory.Inventory;

public class CraftPillager extends CraftMob implements Pillager {

    protected final net.minecraft.world.entity.monster.Pillager pillagerHandle;

    public CraftPillager(net.minecraft.world.entity.monster.Pillager handle) {
        super(handle);
        this.pillagerHandle = handle;
    }

    public net.minecraft.world.entity.monster.Pillager getPillagerHandle() {
        return pillagerHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.PILLAGER;
    }

    @Override
    public Inventory getInventory() { return null; }

    @Override
    public void setRaid(Raid raid) {}
    @Override
    public Raid getRaid() { return null; }
    @Override
    public int getWave() { return 0; }
    @Override
    public void setWave(int wave) {}
    @Override
    public Block getPatrolTarget() { return null; }
    @Override
    public void setPatrolTarget(Block block) {}
    @Override
    public boolean isPatrolLeader() { return false; }
    @Override
    public void setPatrolLeader(boolean leader) {}
    @Override
    public boolean isCanJoinRaid() { return false; }
    @Override
    public void setCanJoinRaid(boolean join) {}
    @Override
    public int getTicksOutsideRaid() { return 0; }
    @Override
    public void setTicksOutsideRaid(int ticks) {}
    @Override
    public boolean isCelebrating() { return false; }
    @Override
    public void setCelebrating(boolean celebrating) {}
    @Override
    public Sound getCelebrationSound() { return null; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftPillager other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftPillager{id=" + getEntityId() + "}";
    }
}
