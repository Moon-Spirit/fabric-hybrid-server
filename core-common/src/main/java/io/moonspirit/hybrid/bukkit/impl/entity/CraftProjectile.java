package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;

public class CraftProjectile extends CraftEntity implements Projectile {

    protected final net.minecraft.world.entity.projectile.Projectile projectileHandle;

    public CraftProjectile(net.minecraft.world.entity.projectile.Projectile handle) {
        super(handle);
        this.projectileHandle = handle;
    }

    public net.minecraft.world.entity.projectile.Projectile getProjectileHandle() {
        return projectileHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }

    @Override
    public ProjectileSource getShooter() { return null; }

    @Override
    public void setShooter(ProjectileSource shooter) {}

    @Override
    public boolean doesBounce() { return handle.noPhysics; }

    @Override
    public void setBounce(boolean doesBounce) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftProjectile other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftProjectile{id=" + getEntityId() + "}";
    }
}
