package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Raid;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vindicator;

public class CraftVindicator extends CraftMob implements Vindicator {

    protected final net.minecraft.world.entity.monster.Vindicator vindicatorHandle;

    public CraftVindicator(net.minecraft.world.entity.monster.Vindicator handle) {
        super(handle);
        this.vindicatorHandle = handle;
    }

    public net.minecraft.world.entity.monster.Vindicator getVindicatorHandle() {
        return vindicatorHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.VINDICATOR;
    }

    @Override
    public boolean isJohnny() { return false; }
    @Override
    public void setJohnny(boolean johnny) {}

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
        if (!(obj instanceof CraftVindicator other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftVindicator{id=" + getEntityId() + "}";
    }
}
