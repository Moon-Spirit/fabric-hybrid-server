package io.moonspirit.hybrid.bukkit.impl.block.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockSupport;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.inventory.ItemStack;

public class CraftBlockData implements BlockData, Cloneable {

    private final Material material;

    public CraftBlockData(Material material) {
        this.material = material;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public String getAsString() {
        return material.getKey().toString();
    }

    @Override
    public String getAsString(boolean hideUnset) {
        return getAsString();
    }

    @Override
    public BlockData merge(BlockData data) {
        return this;
    }

    @Override
    public boolean matches(BlockData data) {
        return data != null && data.getMaterial() == material;
    }

    @Override
    public BlockData clone() {
        try {
            return (BlockData) super.clone();
        } catch (CloneNotSupportedException e) {
            return new CraftBlockData(material);
        }
    }

    @Override
    public SoundGroup getSoundGroup() {
        return null;
    }

    @Override
    public int getLightEmission() {
        return 0;
    }

    @Override
    public boolean isOccluding() {
        return material.isOccluding();
    }

    @Override
    public boolean requiresCorrectToolForDrops() {
        return false;
    }

    @Override
    public boolean isPreferredTool(ItemStack tool) {
        return false;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return PistonMoveReaction.MOVE;
    }

    @Override
    public boolean isSupported(Block block) {
        return true;
    }

    @Override
    public boolean isSupported(Location location) {
        return true;
    }

    @Override
    public boolean isFaceSturdy(BlockFace face, BlockSupport support) {
        return false;
    }

    @Override
    public Material getPlacementMaterial() {
        return material;
    }

    @Override
    public void rotate(StructureRotation rotation) {
    }

    @Override
    public void mirror(Mirror mirror) {
    }

    @Override
    public org.bukkit.block.BlockState createBlockState() {
        return null;
    }
}
