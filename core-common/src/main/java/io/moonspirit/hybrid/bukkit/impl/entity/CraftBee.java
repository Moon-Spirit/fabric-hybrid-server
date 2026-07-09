package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftBee extends CraftMob implements Bee {

    protected final net.minecraft.world.entity.animal.Bee beeHandle;

    public CraftBee(net.minecraft.world.entity.animal.Bee handle) {
        super(handle);
        this.beeHandle = handle;
    }

    public net.minecraft.world.entity.animal.Bee getBeeHandle() {
        return beeHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.BEE;
    }

    @Override
    public Location getHive() { return null; }
    @Override
    public void setHive(Location location) {}
    @Override
    public Location getFlower() { return null; }
    @Override
    public void setFlower(Location location) {}
    @Override
    public boolean hasNectar() { return false; }
    @Override
    public void setHasNectar(boolean nectar) {}
    @Override
    public boolean hasStung() { return false; }
    @Override
    public void setHasStung(boolean stung) {}
    @Override
    public int getAnger() { return 0; }
    @Override
    public void setAnger(int anger) {}
    @Override
    public int getCannotEnterHiveTicks() { return 0; }
    @Override
    public void setCannotEnterHiveTicks(int ticks) {}

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
        if (!(obj instanceof CraftBee other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftBee{id=" + getEntityId() + "}";
    }
}
