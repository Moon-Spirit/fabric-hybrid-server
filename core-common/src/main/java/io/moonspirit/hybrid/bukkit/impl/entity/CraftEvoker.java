package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Raid;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Spellcaster;

public class CraftEvoker extends CraftMob implements Evoker {

    protected final net.minecraft.world.entity.monster.Evoker evokerHandle;

    public CraftEvoker(net.minecraft.world.entity.monster.Evoker handle) {
        super(handle);
        this.evokerHandle = handle;
    }

    public net.minecraft.world.entity.monster.Evoker getEvokerHandle() {
        return evokerHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.EVOKER;
    }

    @Override
    public Evoker.Spell getCurrentSpell() { return Evoker.Spell.NONE; }
    @Override
    public void setCurrentSpell(Evoker.Spell spell) {}

    @Override
    public Spellcaster.Spell getSpell() { return Spellcaster.Spell.NONE; }
    @Override
    public void setSpell(Spellcaster.Spell spell) {}

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
        if (!(obj instanceof CraftEvoker other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftEvoker{id=" + getEntityId() + "}";
    }
}
