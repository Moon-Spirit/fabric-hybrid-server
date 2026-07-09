package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;

import java.util.Set;
import java.util.UUID;

public class CraftSkeleton extends CraftLivingEntity implements Skeleton {

    protected final net.minecraft.world.entity.monster.Skeleton skeletonHandle;

    public CraftSkeleton(net.minecraft.world.entity.monster.Skeleton handle) {
        super(handle);
        this.skeletonHandle = handle;
    }

    public net.minecraft.world.entity.monster.Skeleton getSkeletonHandle() {
        return skeletonHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.SKELETON;
    }

    @Override
    public boolean isConverting() { return false; }

    @Override
    public int getConversionTime() { return 0; }

    @Override
    public void setConversionTime(int time) {}

    @Override
    public Skeleton.SkeletonType getSkeletonType() { return Skeleton.SkeletonType.NORMAL; }

    @Override
    public void setSkeletonType(Skeleton.SkeletonType type) {}

    @Override
    public void setTarget(org.bukkit.entity.LivingEntity target) {}
    @Override
    public org.bukkit.entity.LivingEntity getTarget() { return null; }
    @Override
    public void setAware(boolean aware) {}
    @Override
    public boolean isAware() { return true; }
    @Override
    public void setLootTable(org.bukkit.loot.LootTable table) {}
    @Override
    public org.bukkit.loot.LootTable getLootTable() { return null; }
    @Override
    public void setSeed(long seed) {}
    @Override
    public long getSeed() { return 0; }

    @Override
    public boolean isRiptiding() { return false; }
    @Override
    public boolean isSleeping() { return false; }
    @Override
    public boolean isClimbing() { return false; }
    @Override
    public boolean isSwimming() { return false; }
    @Override
    public void setSwimming(boolean swimming) {}
    @Override
    public org.bukkit.Sound getAmbientSound() { return null; }
    @Override
    public void setGliding(boolean gliding) {}
    @Override
    public boolean isGliding() { return false; }
    @Override
    public void setArrowsInBody(int count) {}
    @Override
    public int getArrowsInBody() { return 0; }
    @Override
    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> type) { return null; }
    @Override
    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> type, org.bukkit.util.Vector velocity) { return null; }
    @Override
    public boolean isCollidable() { return true; }
    @Override
    public void setCollidable(boolean collidable) {}
    @Override
    public Set<UUID> getCollidableExemptions() { return Set.of(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftSkeleton other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftSkeleton{id=" + getEntityId() + "}";
    }
}
