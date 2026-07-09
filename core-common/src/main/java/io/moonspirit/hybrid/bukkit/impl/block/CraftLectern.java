package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.Lectern;
import org.bukkit.inventory.Inventory;

public class CraftLectern extends CraftBlockState implements Lectern {

    public CraftLectern(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public int getPage() { return 0; }

    @Override
    public void setPage(int page) {}

    @Override
    public Inventory getInventory() { return null; }

    @Override
    public Inventory getSnapshotInventory() { return null; }
}
