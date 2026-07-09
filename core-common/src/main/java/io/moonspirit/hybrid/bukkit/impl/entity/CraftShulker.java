package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.DyeColor;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Shulker;

public class CraftShulker extends CraftMob implements Shulker {

    protected final net.minecraft.world.entity.monster.Shulker shulkerHandle;

    public CraftShulker(net.minecraft.world.entity.monster.Shulker handle) {
        super(handle);
        this.shulkerHandle = handle;
    }

    public net.minecraft.world.entity.monster.Shulker getShulkerHandle() {
        return shulkerHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.SHULKER;
    }

    @Override
    public float getPeek() { return 0; }
    @Override
    public void setPeek(float peek) {}
    @Override
    public BlockFace getAttachedFace() { return BlockFace.DOWN; }
    @Override
    public void setAttachedFace(BlockFace face) {}
    @Override
    public DyeColor getColor() { return DyeColor.PURPLE; }
    @Override
    public void setColor(DyeColor color) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftShulker other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftShulker{id=" + getEntityId() + "}";
    }
}
