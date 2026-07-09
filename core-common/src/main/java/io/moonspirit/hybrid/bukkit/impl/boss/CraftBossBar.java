package io.moonspirit.hybrid.bukkit.impl.boss;

import org.bukkit.boss.*;
import org.bukkit.entity.Player;

import java.util.List;

public class CraftBossBar implements BossBar {

    @Override
    public String getTitle() { return null; }

    @Override
    public void setTitle(String title) { }

    @Override
    public BarColor getColor() { return null; }

    @Override
    public void setColor(BarColor color) { }

    @Override
    public BarStyle getStyle() { return null; }

    @Override
    public void setStyle(BarStyle style) { }

    @Override
    public void removeFlag(BarFlag flag) { }

    @Override
    public void addFlag(BarFlag flag) { }

    @Override
    public boolean hasFlag(BarFlag flag) { return false; }

    @Override
    public void setProgress(double progress) { }

    @Override
    public double getProgress() { return 0.0; }

    @Override
    public void addPlayer(Player player) { }

    @Override
    public void removePlayer(Player player) { }

    @Override
    public void removeAll() { }

    @Override
    public List<Player> getPlayers() { return List.of(); }

    @Override
    public void setVisible(boolean visible) { }

    @Override
    public boolean isVisible() { return false; }

    @Override
    public void show() { }

    @Override
    public void hide() { }
}
