package io.moonspirit.hybrid.bukkit.impl.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.Set;

public class CraftScoreboard implements Scoreboard {

    @Override
    public Objective registerNewObjective(String name, String criteria) throws IllegalArgumentException { return null; }

    @Override
    public Objective registerNewObjective(String name, String criteria, String displayName) throws IllegalArgumentException { return null; }

    @Override
    public Objective registerNewObjective(String name, String criteria, String displayName, RenderType renderType) throws IllegalArgumentException { return null; }

    @Override
    public Objective registerNewObjective(String name, Criteria criteria, String displayName) throws IllegalArgumentException { return null; }

    @Override
    public Objective registerNewObjective(String name, Criteria criteria, String displayName, RenderType renderType) throws IllegalArgumentException { return null; }

    @Override
    public Objective getObjective(String name) throws IllegalArgumentException { return null; }

    @Override
    public Set<Objective> getObjectivesByCriteria(String criteria) throws IllegalArgumentException { return Set.of(); }

    @Override
    public Set<Objective> getObjectivesByCriteria(Criteria criteria) throws IllegalArgumentException { return Set.of(); }

    @Override
    public Set<Objective> getObjectives() { return Set.of(); }

    @Override
    public Objective getObjective(DisplaySlot slot) throws IllegalArgumentException { return null; }

    @Override
    public Set<Score> getScores(OfflinePlayer player) throws IllegalArgumentException { return Set.of(); }

    @Override
    public Set<Score> getScores(String entry) throws IllegalArgumentException { return Set.of(); }

    @Override
    public void resetScores(OfflinePlayer player) throws IllegalArgumentException { }

    @Override
    public void resetScores(String entry) throws IllegalArgumentException { }

    @Override
    public Team getPlayerTeam(OfflinePlayer player) throws IllegalArgumentException { return null; }

    @Override
    public Team getEntryTeam(String entry) throws IllegalArgumentException { return null; }

    @Override
    public Team getTeam(String teamName) throws IllegalArgumentException { return null; }

    @Override
    public Set<Team> getTeams() { return Set.of(); }

    @Override
    public Team registerNewTeam(String name) throws IllegalArgumentException { return null; }

    @Override
    public Set<OfflinePlayer> getPlayers() { return Set.of(); }

    @Override
    public Set<String> getEntries() { return Set.of(); }

    @Override
    public void clearSlot(DisplaySlot slot) throws IllegalArgumentException { }
}
