package io.moonspirit.hybrid.bridge.bukkit;

import io.moonspirit.hybrid.bridge.core.server.MinecraftServerBridge;

public interface CraftServerBridge {
    MinecraftServerBridge bridge$getServer();
    void bridge$setPluginManager(org.bukkit.plugin.PluginManager pluginManager);
}
