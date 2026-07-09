package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Goat;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftGoat extends CraftMob implements Goat {

    protected final net.minecraft.world.entity.animal.goat.Goat goatHandle;

    public CraftGoat(net.minecraft.world.entity.animal.goat.Goat handle) {
        super(handle);
        this.goatHandle = handle;
    }

    public net.minecraft.world.entity.animal.goat.Goat getGoatHandle() {
        return goatHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.GOAT;
    }

    @Override
    public boolean hasLeftHorn() { return false; }
    @Override
    public void setLeftHorn(boolean hasHorn) {}
    @Override
    public boolean hasRightHorn() { return false; }
    @Override
    public void setRightHorn(boolean hasHorn) {}
    @Override
    public boolean isScreaming() { return false; }
    @Override
    public void setScreaming(boolean screaming) {}

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
        if (!(obj instanceof CraftGoat other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftGoat{id=" + getEntityId() + "}";
    }
}
