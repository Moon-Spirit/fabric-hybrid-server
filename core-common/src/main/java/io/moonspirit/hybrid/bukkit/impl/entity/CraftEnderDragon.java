package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;

import java.util.Set;

public class CraftEnderDragon extends CraftMob implements EnderDragon {

    protected final net.minecraft.world.entity.boss.enderdragon.EnderDragon dragonHandle;

    public CraftEnderDragon(net.minecraft.world.entity.boss.enderdragon.EnderDragon handle) {
        super(handle);
        this.dragonHandle = handle;
    }

    public net.minecraft.world.entity.boss.enderdragon.EnderDragon getDragonHandle() {
        return dragonHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDER_DRAGON;
    }

    @Override
    public Phase getPhase() { return Phase.HOVER; }

    @Override
    public void setPhase(Phase phase) {}

    @Override
    public DragonBattle getDragonBattle() { return null; }

    @Override
    public int getDeathAnimationTicks() { return 0; }

    @Override
    public BossBar getBossBar() { return null; }

    @Override
    public Set<ComplexEntityPart> getParts() { return Set.of(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftEnderDragon other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftEnderDragon{id=" + getEntityId() + "}";
    }
}
