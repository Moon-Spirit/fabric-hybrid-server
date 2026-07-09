package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;

public class CraftWither extends CraftMob implements Wither {

    protected final net.minecraft.world.entity.boss.wither.WitherBoss witherHandle;

    public CraftWither(net.minecraft.world.entity.boss.wither.WitherBoss handle) {
        super(handle);
        this.witherHandle = handle;
    }

    public net.minecraft.world.entity.boss.wither.WitherBoss getWitherHandle() {
        return witherHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.WITHER;
    }

    @Override
    public void setTarget(LivingEntity target) {}

    @Override
    public LivingEntity getTarget() { return null; }

    @Override
    public void setTarget(Head head, LivingEntity target) {}

    @Override
    public LivingEntity getTarget(Head head) { return null; }

    @Override
    public int getInvulnerabilityTicks() { return witherHandle.getInvulnerableTicks(); }

    @Override
    public void setInvulnerabilityTicks(int ticks) { witherHandle.setInvulnerableTicks(ticks); }

    @Override
    public BossBar getBossBar() { return null; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftWither other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftWither{id=" + getEntityId() + "}";
    }
}
