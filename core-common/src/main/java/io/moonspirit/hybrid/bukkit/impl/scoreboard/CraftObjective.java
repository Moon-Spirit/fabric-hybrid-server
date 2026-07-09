package io.moonspirit.hybrid.bukkit.impl.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

public class CraftObjective implements Objective {

    @Override
    public String getName() { return null; }

    @Override
    public String getDisplayName() throws IllegalStateException { return null; }

    @Override
    public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException { }

    @Override
    public String getCriteria() { return null; }

    @Override
    public Criteria getTrackedCriteria() { return null; }

    @Override
    public boolean isModifiable() { return false; }

    @Override
    public Scoreboard getScoreboard() { return null; }

    @Override
    public void unregister() throws IllegalStateException { }

    @Override
    public void setDisplaySlot(DisplaySlot slot) throws IllegalStateException { }

    @Override
    public DisplaySlot getDisplaySlot() throws IllegalStateException { return null; }

    @Override
    public void setRenderType(RenderType renderType) throws IllegalStateException { }

    @Override
    public RenderType getRenderType() throws IllegalStateException { return null; }

    @Override
    public Score getScore(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException { return null; }

    @Override
    public Score getScore(String entry) throws IllegalArgumentException, IllegalStateException { return null; }
}
