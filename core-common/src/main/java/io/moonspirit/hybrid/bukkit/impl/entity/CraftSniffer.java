package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sniffer;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CraftSniffer extends CraftMob implements Sniffer {

    protected final net.minecraft.world.entity.animal.sniffer.Sniffer snifferHandle;

    public CraftSniffer(net.minecraft.world.entity.animal.sniffer.Sniffer handle) {
        super(handle);
        this.snifferHandle = handle;
    }

    public net.minecraft.world.entity.animal.sniffer.Sniffer getSnifferHandle() {
        return snifferHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.SNIFFER;
    }

    @Override
    public Collection<Location> getExploredLocations() { return List.of(); }
    @Override
    public void removeExploredLocation(Location location) {}
    @Override
    public void addExploredLocation(Location location) {}
    @Override
    public Sniffer.State getState() { return Sniffer.State.IDLING; }
    @Override
    public void setState(Sniffer.State state) {}
    @Override
    public Location findPossibleDigLocation() { return null; }
    @Override
    public boolean canDig() { return false; }

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
        if (!(obj instanceof CraftSniffer other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftSniffer{id=" + getEntityId() + "}";
    }
}
