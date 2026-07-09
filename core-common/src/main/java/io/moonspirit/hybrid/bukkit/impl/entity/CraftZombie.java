package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

import java.util.Set;
import java.util.UUID;

public class CraftZombie extends CraftLivingEntity implements Zombie {

    protected final net.minecraft.world.entity.monster.Zombie zombieHandle;

    public CraftZombie(net.minecraft.world.entity.monster.Zombie handle) {
        super(handle);
        this.zombieHandle = handle;
    }

    public net.minecraft.world.entity.monster.Zombie getZombieHandle() {
        return zombieHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public boolean isBaby() { return zombieHandle.isBaby(); }

    @Override
    public void setBaby(boolean baby) { zombieHandle.setBaby(baby); }

    @Override
    public boolean isVillager() { return false; }

    @Override
    public void setVillager(boolean villager) {}

    @Override
    public void setVillagerProfession(Villager.Profession profession) {}

    @Override
    public Villager.Profession getVillagerProfession() { return null; }

    @Override
    public boolean isConverting() { return false; }

    @Override
    public int getConversionTime() { return 0; }

    @Override
    public void setConversionTime(int time) {}

    @Override
    public boolean canBreakDoors() { return zombieHandle.canBreakDoors(); }

    @Override
    public void setCanBreakDoors(boolean canBreakDoors) { zombieHandle.setCanBreakDoors(canBreakDoors); }

    @Override
    public int getAge() { return 0; }
    @Override
    public void setAge(int age) {}
    @Override
    public void setAgeLock(boolean lock) {}
    @Override
    public boolean getAgeLock() { return false; }
    @Override
    public void setAdult() { setBaby(false); }
    @Override
    public void setBaby() { setBaby(true); }
    @Override
    public boolean isAdult() { return !isBaby(); }
    @Override
    public boolean canBreed() { return false; }
    @Override
    public void setBreed(boolean breed) {}

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
        if (!(obj instanceof CraftZombie other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftZombie{id=" + getEntityId() + "}";
    }
}
