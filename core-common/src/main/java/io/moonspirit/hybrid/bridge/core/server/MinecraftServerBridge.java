package io.moonspirit.hybrid.bridge.core.server;

import net.minecraft.commands.Commands;
import org.bukkit.Server;

public interface MinecraftServerBridge {
    void bridge$setCraftServer(Server server);
    Server bridge$getCraftServer();
    void bridge$queuedProcess(Runnable runnable);
    Commands bridge$getVanillaCommands();
}
