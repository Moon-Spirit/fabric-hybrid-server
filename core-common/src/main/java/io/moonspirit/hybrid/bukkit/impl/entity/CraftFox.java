package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftFox extends CraftMob implements Fox {

    protected final net.minecraft.world.entity.animal.Fox foxHandle;

    public CraftFox(net.minecraft.world.entity.animal.Fox handle) {
        super(handle);
        this.foxHandle = handle;
    }

    public net.minecraft.world.entity.animal.Fox getFoxHandle() {
        return foxHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.FOX;
    }

    @Override
    public Fox.Type getFoxType() { return Fox.Type.RED; }
    @Override
    public void setFoxType(Fox.Type type) {}
    @Override
    public boolean isCrouching() { return false; }
    @Override
    public void setCrouching(boolean crouching) {}
    @Override
    public void setSleeping(boolean sleeping) {}
    @Override
    public boolean isSitting() { return false; }
    @Override
    public void setSitting(boolean sitting) {}
    @Override
    public AnimalTamer getFirstTrustedPlayer() { return null; }
    @Override
    public void setFirstTrustedPlayer(AnimalTamer player) {}
    @Override
    public AnimalTamer getSecondTrustedPlayer() { return null; }
    @Override
    public void setSecondTrustedPlayer(AnimalTamer player) {}
    @Override
    public boolean isFaceplanted() { return false; }

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
        if (!(obj instanceof CraftFox other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftFox{id=" + getEntityId() + "}";
    }
}
