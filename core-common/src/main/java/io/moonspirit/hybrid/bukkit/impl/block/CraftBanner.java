package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.DyeColor;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;

import java.util.ArrayList;
import java.util.List;

public class CraftBanner extends CraftBlockState implements Banner {

    public CraftBanner(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public DyeColor getBaseColor() { return DyeColor.WHITE; }

    @Override
    public void setBaseColor(DyeColor color) {}

    @Override
    public List<Pattern> getPatterns() { return new ArrayList<>(); }

    @Override
    public void setPatterns(List<Pattern> patterns) {}

    @Override
    public void addPattern(Pattern pattern) {}

    @Override
    public Pattern getPattern(int index) { return null; }

    @Override
    public Pattern removePattern(int index) { return null; }

    @Override
    public void setPattern(int index, Pattern pattern) {}

    @Override
    public int numberOfPatterns() { return 0; }
}
