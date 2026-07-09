package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

public class CraftMinecart extends CraftEntity implements Minecart {

    protected final net.minecraft.world.entity.vehicle.AbstractMinecart minecartHandle;

    public CraftMinecart(net.minecraft.world.entity.vehicle.AbstractMinecart handle) {
        super(handle);
        this.minecartHandle = handle;
    }

    public net.minecraft.world.entity.vehicle.AbstractMinecart getMinecartHandle() {
        return minecartHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.MINECART;
    }

    @Override
    public void setDamage(double damage) {}

    @Override
    public double getDamage() { return 0; }

    @Override
    public double getMaxSpeed() { return 0.4; }

    @Override
    public void setMaxSpeed(double speed) {}

    @Override
    public boolean isSlowWhenEmpty() { return false; }

    @Override
    public void setSlowWhenEmpty(boolean slow) {}

    @Override
    public Vector getFlyingVelocityMod() { return new Vector(0, 0, 0); }

    @Override
    public void setFlyingVelocityMod(Vector flying) {}

    @Override
    public Vector getDerailedVelocityMod() { return new Vector(0, 0, 0); }

    @Override
    public void setDerailedVelocityMod(Vector derailed) {}

    @Override
    public void setDisplayBlock(MaterialData material) {}

    @Override
    public MaterialData getDisplayBlock() { return null; }

    @Override
    public void setDisplayBlockData(BlockData blockData) {}

    @Override
    public BlockData getDisplayBlockData() { return null; }

    @Override
    public void setDisplayBlockOffset(int offset) {}

    @Override
    public int getDisplayBlockOffset() { return 6; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftMinecart other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftMinecart{id=" + getEntityId() + "}";
    }
}
