package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.DyeColor;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;

public class CraftSign extends CraftBlockState implements org.bukkit.block.Sign {

    public CraftSign(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public String[] getLines() {
        return new String[]{"", "", "", ""};
    }

    @Override
    public String getLine(int index) throws IndexOutOfBoundsException {
        return "";
    }

    @Override
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public void setEditable(boolean editable) {
    }

    @Override
    public boolean isWaxed() {
        return false;
    }

    @Override
    public void setWaxed(boolean waxed) {
    }

    @Override
    public boolean isGlowingText() {
        return false;
    }

    @Override
    public void setGlowingText(boolean glowingText) {
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.BLACK;
    }

    @Override
    public void setColor(DyeColor color) {
    }

    @Override
    public SignSide getSide(Side side) {
        return null;
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
