package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.Material;
import org.bukkit.block.Jukebox;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.JukeboxInventory;

public class CraftJukebox extends CraftBlockState implements Jukebox {

    public CraftJukebox(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public Material getPlaying() { return null; }

    @Override
    public void setPlaying(Material playing) {}

    @Override
    public boolean hasRecord() { return false; }

    @Override
    public ItemStack getRecord() { return null; }

    @Override
    public void setRecord(ItemStack record) {}

    @Override
    public boolean isPlaying() { return false; }

    @Override
    public boolean startPlaying() { return false; }

    @Override
    public void stopPlaying() {}

    @Override
    public boolean eject() { return false; }

    @Override
    public JukeboxInventory getInventory() { return null; }

    @Override
    public JukeboxInventory getSnapshotInventory() { return null; }
}
