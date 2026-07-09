package io.moonspirit.hybrid.bukkit.impl.structure;

import org.bukkit.Location;
import org.bukkit.RegionAccessor;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.structure.Palette;
import org.bukkit.structure.Structure;
import org.bukkit.util.BlockVector;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CraftStructure implements Structure {

    @Override
    public BlockVector getSize() {
        return new BlockVector(0, 0, 0);
    }

    @Override
    public List<Palette> getPalettes() {
        return Collections.emptyList();
    }

    @Override
    public int getPaletteCount() {
        return 0;
    }

    @Override
    public List<Entity> getEntities() {
        return Collections.emptyList();
    }

    @Override
    public int getEntityCount() {
        return 0;
    }

    @Override
    public void place(Location location, boolean includeEntities, StructureRotation rotation, Mirror mirror, int palette, float integrity, Random random) {
    }

    @Override
    public void place(RegionAccessor regionAccessor, BlockVector blockVector, boolean includeEntities, StructureRotation rotation, Mirror mirror, int palette, float integrity, Random random) {
    }

    @Override
    public void fill(Location corner1, Location corner2, boolean includeEntities) {
    }

    @Override
    public void fill(Location corner1, BlockVector blockVector, boolean includeEntities) {
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
