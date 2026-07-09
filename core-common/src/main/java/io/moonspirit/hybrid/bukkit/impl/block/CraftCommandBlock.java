package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.CommandBlock;

public class CraftCommandBlock extends CraftBlockState implements CommandBlock {

    public CraftCommandBlock(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public String getCommand() { return ""; }

    @Override
    public void setCommand(String command) {}

    @Override
    public String getName() { return ""; }

    @Override
    public void setName(String name) {}
}
