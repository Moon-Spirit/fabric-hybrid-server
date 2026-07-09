package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.block.Bell;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;

public class CraftBell extends CraftBlockState implements Bell {

    public CraftBell(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public boolean ring(Entity entity, BlockFace direction) { return false; }

    @Override
    public boolean ring(Entity entity) { return false; }

    @Override
    public boolean ring(BlockFace direction) { return false; }

    @Override
    public boolean ring() { return false; }

    @Override
    public boolean isShaking() { return false; }

    @Override
    public int getShakingTicks() { return 0; }

    @Override
    public boolean isResonating() { return false; }

    @Override
    public int getResonatingTicks() { return 0; }
}
