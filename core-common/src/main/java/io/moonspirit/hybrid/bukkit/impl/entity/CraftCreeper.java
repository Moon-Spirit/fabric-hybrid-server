package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;

import java.util.Set;
import java.util.UUID;

public class CraftCreeper extends CraftLivingEntity implements Creeper {

    protected final net.minecraft.world.entity.monster.Creeper creeperHandle;

    public CraftCreeper(net.minecraft.world.entity.monster.Creeper handle) {
        super(handle);
        this.creeperHandle = handle;
    }

    public net.minecraft.world.entity.monster.Creeper getCreeperHandle() {
        return creeperHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.CREEPER;
    }

    @Override
    public boolean isPowered() { return creeperHandle.isPowered(); }

    @Override
    public void setPowered(boolean powered) {}

    @Override
    public void setMaxFuseTicks(int ticks) {}

    @Override
    public int getMaxFuseTicks() { return 30; }

    @Override
    public void setFuseTicks(int ticks) {}

    @Override
    public int getFuseTicks() { return creeperHandle.getSwellDir(); }

    @Override
    public void setExplosionRadius(int radius) {}

    @Override
    public int getExplosionRadius() { return 3; }

    @Override
    public void explode() {
        var level = creeperHandle.level();
        if (level != null) {
            level.explode(creeperHandle, creeperHandle.getX(), creeperHandle.getY(), creeperHandle.getZ(), 3.0f, net.minecraft.world.level.Level.ExplosionInteraction.MOB);
        }
    }

    @Override
    public void ignite() { creeperHandle.ignite(); }

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
        if (!(obj instanceof CraftCreeper other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftCreeper{id=" + getEntityId() + "}";
    }
}
