package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftWolf extends CraftMob implements Wolf {

    protected final net.minecraft.world.entity.animal.Wolf wolfHandle;

    public CraftWolf(net.minecraft.world.entity.animal.Wolf handle) {
        super(handle);
        this.wolfHandle = handle;
    }

    public net.minecraft.world.entity.animal.Wolf getWolfHandle() {
        return wolfHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.WOLF;
    }

    @Override
    public boolean isAngry() { return wolfHandle.isAngry(); }

    @Override
    public void setAngry(boolean angry) {}

    @Override
    public DyeColor getCollarColor() { return DyeColor.RED; }

    @Override
    public void setCollarColor(DyeColor color) {}

    @Override
    public boolean isWet() { return wolfHandle.isWet(); }

    @Override
    public float getTailAngle() { return 0; }

    @Override
    public boolean isInterested() { return false; }

    @Override
    public void setInterested(boolean interested) {}

    @Override
    public boolean isTamed() { return wolfHandle.isTame(); }

    @Override
    public void setTamed(boolean tamed) {}

    @Override
    public AnimalTamer getOwner() { return null; }

    @Override
    public void setOwner(AnimalTamer owner) {}

    @Override
    public boolean isSitting() { return wolfHandle.isInSittingPose(); }

    @Override
    public void setSitting(boolean sitting) { wolfHandle.setInSittingPose(sitting); }

    // ===== Animals =====

    @Override
    public UUID getBreedCause() { return null; }

    @Override
    public void setBreedCause(UUID cause) {}

    @Override
    public boolean isLoveMode() { return false; }

    @Override
    public int getLoveModeTicks() { return 0; }

    @Override
    public void setLoveModeTicks(int ticks) {}

    @Override
    public boolean isBreedItem(ItemStack stack) { return false; }

    @Override
    public boolean isBreedItem(Material material) { return false; }

    // ===== Ageable =====

    @Override
    public int getAge() { return 0; }

    @Override
    public void setAge(int age) {}

    @Override
    public void setAgeLock(boolean lock) {}

    @Override
    public boolean getAgeLock() { return false; }

    @Override
    public void setBaby() {}

    @Override
    public void setAdult() {}

    @Override
    public boolean isAdult() { return true; }

    @Override
    public boolean canBreed() { return false; }

    @Override
    public void setBreed(boolean breed) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftWolf other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftWolf{id=" + getEntityId() + "}";
    }
}
