package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.EnchantingTable;

public class CraftEnchantingTable extends CraftBlockState implements EnchantingTable {

    public CraftEnchantingTable(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
    }
}
