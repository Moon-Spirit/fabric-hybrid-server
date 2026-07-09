package io.moonspirit.hybrid.bukkit.impl.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CraftCommandMap implements CommandMap {

    private final Map<String, Command> knownCommands = new ConcurrentHashMap<>();

    @Override
    public void registerAll(String label, List<Command> commands) {
        for (Command cmd : commands) {
            register(label, cmd);
        }
    }

    @Override
    public boolean register(String label, String fallbackPrefix, Command command) {
        String key = fallbackPrefix + ":" + command.getName();
        knownCommands.put(key, command);
        return true;
    }

    @Override
    public boolean register(String label, Command command) {
        knownCommands.put(command.getName(), command);
        for (String alias : command.getAliases()) {
            knownCommands.put(alias, command);
        }
        return true;
    }

    @Override
    public boolean dispatch(CommandSender sender, String commandLine) throws CommandException {
        String[] split = commandLine.split(" ", 2);
        String name = split[0];
        String[] args = split.length > 1 ? split[1].split(" ") : new String[0];
        Command cmd = getCommand(name);
        if (cmd != null) {
            try {
                return cmd.execute(sender, name, args);
            } catch (Exception e) {
                throw new CommandException("Error executing command " + name, e);
            }
        }
        return false;
    }

    @Override
    public void clearCommands() {
        knownCommands.clear();
    }

    @Override
    public Command getCommand(String name) {
        return knownCommands.get(name.toLowerCase());
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String commandLine) throws IllegalArgumentException {
        return Collections.emptyList();
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String commandLine, Location location) throws IllegalArgumentException {
        return Collections.emptyList();
    }
}
