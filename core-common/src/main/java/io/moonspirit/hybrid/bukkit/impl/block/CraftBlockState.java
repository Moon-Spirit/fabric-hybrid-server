package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftBlock;
import io.moonspirit.hybrid.bukkit.impl.CraftChunk;
import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;

public class CraftBlockState implements BlockState {

    protected final CraftWorld craftWorld;
    protected final BlockPos position;
    protected Material type;

    public CraftBlockState(CraftWorld world, BlockPos pos) {
        this.craftWorld = world;
        this.position = pos;
        this.type = CraftBlock.toBukkitMaterial(world.getHandle().getBlockState(pos).getBlock());
    }

    @Override
    public Block getBlock() {
        return new CraftBlock(craftWorld, position);
    }

    @Override
    public MaterialData getData() {
        return null;
    }

    @Override
    public BlockData getBlockData() {
        return null;
    }

    @Override
    public Material getType() {
        return type;
    }

    @Override
    public byte getLightLevel() {
        return 0;
    }

    @Override
    public World getWorld() {
        return craftWorld;
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public int getZ() {
        return position.getZ();
    }

    @Override
    public Location getLocation() {
        return new Location(craftWorld, position.getX(), position.getY(), position.getZ());
    }

    @Override
    public Location getLocation(Location loc) {
        loc.setWorld(craftWorld);
        loc.setX(position.getX());
        loc.setY(position.getY());
        loc.setZ(position.getZ());
        return loc;
    }

    @Override
    public Chunk getChunk() {
        return craftWorld.getChunkAt(position.getX() >> 4, position.getZ() >> 4);
    }

    @Override
    public void setData(MaterialData data) {
    }

    @Override
    public void setBlockData(BlockData data) {
    }

    @Override
    public void setType(Material type) {
        this.type = type;
    }

    @Override
    public boolean update() {
        return update(false, true);
    }

    @Override
    public boolean update(boolean force) {
        return update(force, true);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        return true;
    }

    @Override
    public byte getRawData() {
        return 0;
    }

    @Override
    public void setRawData(byte data) {
    }

    @Override
    public boolean isPlaced() {
        return true;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue metadataValue) {
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return Collections.emptyList();
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin plugin) {
    }
}
