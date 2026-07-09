package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.TreeSpecies;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;

public class CraftBoat extends CraftEntity implements Boat {

    protected final net.minecraft.world.entity.vehicle.Boat boatHandle;

    public CraftBoat(net.minecraft.world.entity.vehicle.Boat handle) {
        super(handle);
        this.boatHandle = handle;
    }

    public net.minecraft.world.entity.vehicle.Boat getBoatHandle() {
        return boatHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.BOAT;
    }

    @Override
    public TreeSpecies getWoodType() { return TreeSpecies.GENERIC; }

    @Override
    public void setWoodType(TreeSpecies species) {}

    @Override
    public Type getBoatType() { return Type.OAK; }

    @Override
    public void setBoatType(Type type) {}

    @Override
    public double getMaxSpeed() { return 0.4; }

    @Override
    public void setMaxSpeed(double speed) {}

    @Override
    public double getOccupiedDeceleration() { return 0.2; }

    @Override
    public void setOccupiedDeceleration(double deceleration) {}

    @Override
    public double getUnoccupiedDeceleration() { return -1.0; }

    @Override
    public void setUnoccupiedDeceleration(double deceleration) {}

    @Override
    public boolean getWorkOnLand() { return false; }

    @Override
    public void setWorkOnLand(boolean workOnLand) {}

    @Override
    public Status getStatus() { return Status.IN_WATER; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftBoat other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftBoat{id=" + getEntityId() + "}";
    }
}
