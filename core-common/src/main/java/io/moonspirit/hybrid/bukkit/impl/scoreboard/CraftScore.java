package io.moonspirit.hybrid.bukkit.impl.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class CraftScore implements Score {

    @Override
    public OfflinePlayer getPlayer() { return null; }

    @Override
    public String getEntry() { return null; }

    @Override
    public Objective getObjective() { return null; }

    @Override
    public int getScore() { return 0; }

    @Override
    public void setScore(int score) throws IllegalStateException { }

    @Override
    public boolean isScoreSet() { return false; }

    @Override
    public Scoreboard getScoreboard() { return null; }
}
