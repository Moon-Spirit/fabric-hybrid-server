package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftCat extends CraftMob implements Cat {

    protected final net.minecraft.world.entity.animal.Cat catHandle;

    public CraftCat(net.minecraft.world.entity.animal.Cat handle) {
        super(handle);
        this.catHandle = handle;
    }

    public net.minecraft.world.entity.animal.Cat getCatHandle() {
        return catHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.CAT;
    }

    @Override
    public Type getCatType() { return Type.TABBY; }

    @Override
    public void setCatType(Type type) {}

    @Override
    public DyeColor getCollarColor() { return DyeColor.RED; }

    @Override
    public void setCollarColor(DyeColor color) {}

    @Override
    public boolean isTamed() { return catHandle.isTame(); }

    @Override
    public void setTamed(boolean tamed) {}

    @Override
    public AnimalTamer getOwner() { return null; }

    @Override
    public void setOwner(AnimalTamer owner) {}

    @Override
    public boolean isSitting() { return catHandle.isInSittingPose(); }

    @Override
    public void setSitting(boolean sitting) { catHandle.setInSittingPose(sitting); }

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
        if (!(obj instanceof CraftCat other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftCat{id=" + getEntityId() + "}";
    }
}
