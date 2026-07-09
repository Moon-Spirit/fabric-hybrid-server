package io.moonspirit.hybrid.bukkit.impl.generator;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

public class CraftChunkData implements ChunkGenerator.ChunkData {

    private final int minHeight;
    private final int maxHeight;

    public CraftChunkData(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public int getMinHeight() {
        return minHeight;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public Biome getBiome(int x, int y, int z) {
        return null;
    }

    @Override
    public void setBlock(int x, int y, int z, Material material) {
    }

    @Override
    public void setBlock(int x, int y, int z, MaterialData material) {
    }

    @Override
    public void setBlock(int x, int y, int z, BlockData blockData) {
    }

    @Override
    public void setRegion(int x1, int y1, int z1, int x2, int y2, int z2, Material material) {
    }

    @Override
    public void setRegion(int x1, int y1, int z1, int x2, int y2, int z2, MaterialData material) {
    }

    @Override
    public void setRegion(int x1, int y1, int z1, int x2, int y2, int z2, BlockData blockData) {
    }

    @Override
    public Material getType(int x, int y, int z) {
        return null;
    }

    @Override
    public MaterialData getTypeAndData(int x, int y, int z) {
        return null;
    }

    @Override
    public BlockData getBlockData(int x, int y, int z) {
        return null;
    }

    @Override
    public byte getData(int x, int y, int z) {
        return 0;
    }
}
