package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;

public class CraftPig extends CraftMob implements Pig {

    protected final net.minecraft.world.entity.animal.Pig pigHandle;

    public CraftPig(net.minecraft.world.entity.animal.Pig handle) {
        super(handle);
        this.pigHandle = handle;
    }

    public net.minecraft.world.entity.animal.Pig getPigHandle() {
        return pigHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.PIG;
    }

    @Override
    public boolean hasSaddle() { return false; }
    @Override
    public void setSaddle(boolean saddle) {}
    @Override
    public int getBoostTicks() { return 0; }
    @Override
    public void setBoostTicks(int ticks) {}
    @Override
    public int getCurrentBoostTicks() { return 0; }
    @Override
    public void setCurrentBoostTicks(int ticks) {}
    @Override
    public Material getSteerMaterial() { return null; }
    @Override
    public Vector getVelocity() { return getVelocity(); }
    @Override
    public void setVelocity(Vector velocity) { setVelocity(velocity); }

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
        if (!(obj instanceof CraftPig other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftPig{id=" + getEntityId() + "}";
    }
}
