package io.moonspirit.hybrid.bukkit.impl.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.Set;

public class CraftTeam implements Team {

    @Override
    public String getName() { return null; }

    @Override
    public String getDisplayName() { return null; }

    @Override
    public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException { }

    @Override
    public String getPrefix() { return null; }

    @Override
    public void setPrefix(String prefix) throws IllegalStateException, IllegalArgumentException { }

    @Override
    public String getSuffix() { return null; }

    @Override
    public void setSuffix(String suffix) throws IllegalStateException, IllegalArgumentException { }

    @Override
    public ChatColor getColor() { return null; }

    @Override
    public void setColor(ChatColor color) { }

    @Override
    public boolean allowFriendlyFire() { return false; }

    @Override
    public void setAllowFriendlyFire(boolean enabled) { }

    @Override
    public boolean canSeeFriendlyInvisibles() { return false; }

    @Override
    public void setCanSeeFriendlyInvisibles(boolean enabled) { }

    @Override
    public NameTagVisibility getNameTagVisibility() { return null; }

    @Override
    public void setNameTagVisibility(NameTagVisibility visibility) { }

    @Override
    public Set<OfflinePlayer> getPlayers() { return Set.of(); }

    @Override
    public Set<String> getEntries() { return Set.of(); }

    @Override
    public int getSize() { return 0; }

    @Override
    public Scoreboard getScoreboard() { return null; }

    @Override
    public void addPlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException { }

    @Override
    public void addEntry(String entry) throws IllegalStateException, IllegalArgumentException { }

    @Override
    public boolean removePlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException { return false; }

    @Override
    public boolean removeEntry(String entry) throws IllegalStateException, IllegalArgumentException { return false; }

    @Override
    public void unregister() throws IllegalStateException { }

    @Override
    public boolean hasPlayer(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException { return false; }

    @Override
    public boolean hasEntry(String entry) throws IllegalArgumentException, IllegalStateException { return false; }

    @Override
    public OptionStatus getOption(Option option) throws IllegalStateException { return null; }

    @Override
    public void setOption(Option option, OptionStatus status) throws IllegalStateException { }
}
