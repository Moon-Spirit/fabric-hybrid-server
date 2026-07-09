package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftFrog extends CraftMob implements Frog {

    protected final net.minecraft.world.entity.animal.frog.Frog frogHandle;

    public CraftFrog(net.minecraft.world.entity.animal.frog.Frog handle) {
        super(handle);
        this.frogHandle = handle;
    }

    public net.minecraft.world.entity.animal.frog.Frog getFrogHandle() {
        return frogHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.FROG;
    }

    @Override
    public Entity getTongueTarget() { return null; }
    @Override
    public void setTongueTarget(Entity target) {}
    @Override
    public Frog.Variant getVariant() { return Frog.Variant.TEMPERATE; }
    @Override
    public void setVariant(Frog.Variant variant) {}

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
        if (!(obj instanceof CraftFrog other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftFrog{id=" + getEntityId() + "}";
    }
}
