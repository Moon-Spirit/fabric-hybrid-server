package io.moonspirit.hybrid.bukkit.impl;

import io.moonspirit.hybrid.bukkit.impl.entity.CraftEntity;
import io.moonspirit.hybrid.bridge.core.world.level.LevelChunkBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.Collections;

public class CraftChunk implements Chunk {

    private final LevelChunk handle;
    private final CraftWorld craftWorld;

    public CraftChunk(LevelChunk handle, CraftWorld craftWorld) {
        this.handle = handle;
        this.craftWorld = craftWorld;
    }

    public LevelChunk getHandle() {
        return handle;
    }

    @Override
    public int getX() {
        return handle.getPos().x;
    }

    @Override
    public int getZ() {
        return handle.getPos().z;
    }

    @Override
    public World getWorld() {
        return craftWorld;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return new CraftBlock(craftWorld, new BlockPos((getX() << 4) + x, y, (getZ() << 4) + z));
    }

    @Override
    public ChunkSnapshot getChunkSnapshot() {
        return getChunkSnapshot(true, false, false);
    }

    @Override
    public ChunkSnapshot getChunkSnapshot(boolean includeMaxBlockY, boolean includeBiome, boolean includeBiomeTempRain) {
        return null;
    }

    @Override
    public boolean isEntitiesLoaded() {
        return false;
    }

    @Override
    public Entity[] getEntities() {
        ServerLevel level = craftWorld.getHandle();
        java.util.List<net.minecraft.world.entity.Entity> chunkEntities = new java.util.ArrayList<>();
        int cx = getX();
        int cz = getZ();
        net.minecraft.world.level.entity.EntityTypeTest<net.minecraft.world.entity.Entity, net.minecraft.world.entity.Entity> typeTest =
            net.minecraft.world.level.entity.EntityTypeTest.forClass(net.minecraft.world.entity.Entity.class);
        net.minecraft.world.phys.AABB aabb = new net.minecraft.world.phys.AABB(
            Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
            Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (net.minecraft.world.entity.Entity entity : level.getEntities(typeTest, aabb, e -> true)) {
            int ex = ((int) Math.floor(entity.getX())) >> 4;
            int ez = ((int) Math.floor(entity.getZ())) >> 4;
            if (ex == cx && ez == cz) {
                chunkEntities.add(entity);
            }
        }
        Entity[] result = new Entity[chunkEntities.size()];
        for (int i = 0; i < chunkEntities.size(); i++) {
            result[i] = new CraftEntity(chunkEntities.get(i));
        }
        return result;
    }

    @Override
    public BlockState[] getTileEntities() {
        return new BlockState[0];
    }

    @Override
    public boolean isGenerated() {
        return true;
    }

    @Override
    public boolean isLoaded() {
        return craftWorld.getHandle().getChunkSource().hasChunk(getX(), getZ());
    }

    @Override
    public boolean load(boolean generate) {
        craftWorld.getHandle().getChunkSource().getChunk(getX(), getZ(), generate);
        return true;
    }

    @Override
    public boolean load() {
        return load(true);
    }

    @Override
    public boolean unload(boolean save) {
        return false; // TODO: implement chunk unloading via ticket system
    }

    @Override
    public boolean unload() {
        return unload(true);
    }

    @Override
    public boolean isSlimeChunk() {
        return false;
    }

    @Override
    public boolean isForceLoaded() {
        return false;
    }

    @Override
    public void setForceLoaded(boolean forced) {
    }

    @Override
    public boolean addPluginChunkTicket(Plugin plugin) {
        return false;
    }

    @Override
    public boolean removePluginChunkTicket(Plugin plugin) {
        return false;
    }

    @Override
    public Collection<Plugin> getPluginChunkTickets() {
        return Collections.emptyList();
    }

    @Override
    public long getInhabitedTime() {
        return handle.getInhabitedTime();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        handle.setInhabitedTime(ticks);
    }

    @Override
    public boolean contains(BlockData blockData) {
        return false;
    }

    @Override
    public boolean contains(Biome biome) {
        return false;
    }

    @Override
    public LoadLevel getLoadLevel() {
        return LoadLevel.ENTITY_TICKING;
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
