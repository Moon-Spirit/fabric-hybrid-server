package io.moonspirit.hybrid.bukkit.impl.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CraftCommand extends Command {

    private final String actualName;

    public CraftCommand(String name) {
        super(name);
        this.actualName = name;
    }

    public CraftCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
        this.actualName = name;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return false;
    }

    @Override
    public String toString() {
        return "CraftCommand{name=" + actualName + "}";
    }
}
