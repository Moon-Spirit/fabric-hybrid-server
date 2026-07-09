package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.DyeColor;
import org.bukkit.block.ShulkerBox;

public class CraftShulkerBox extends CraftBlockState implements ShulkerBox {

    public CraftShulkerBox(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public org.bukkit.inventory.Inventory getInventory() {
        return null;
    }

    @Override
    public org.bukkit.inventory.Inventory getSnapshotInventory() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public String getLock() {
        return null;
    }

    @Override
    public void setLock(String key) {
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
    }

    @Override
    public org.bukkit.loot.LootTable getLootTable() {
        return null;
    }

    @Override
    public void setLootTable(org.bukkit.loot.LootTable table) {
    }

    @Override
    public long getSeed() {
        return 0L;
    }

    @Override
    public void setSeed(long seed) {
    }

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    @Override
    public DyeColor getColor() { return DyeColor.PURPLE; }
}
