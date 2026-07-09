package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Turtle;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftTurtle extends CraftMob implements Turtle {

    protected final net.minecraft.world.entity.animal.Turtle turtleHandle;

    public CraftTurtle(net.minecraft.world.entity.animal.Turtle handle) {
        super(handle);
        this.turtleHandle = handle;
    }

    public net.minecraft.world.entity.animal.Turtle getTurtleHandle() {
        return turtleHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.TURTLE;
    }

    @Override
    public boolean hasEgg() { return false; }
    @Override
    public boolean isLayingEgg() { return false; }

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
        if (!(obj instanceof CraftTurtle other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftTurtle{id=" + getEntityId() + "}";
    }
}
