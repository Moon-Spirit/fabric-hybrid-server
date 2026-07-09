package io.moonspirit.hybrid.fabric;

import io.moonspirit.hybrid.bukkit.impl.CraftServer;
import io.moonspirit.hybrid.mod.server.BukkitRegistry;
import io.moonspirit.hybrid.mod.server.HybridServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class HybridFabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("[Hybrid] Fabric Hybrid Server initializing...");

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            System.out.println("[Hybrid] Server is starting...");
            HybridServer hybridServer = HybridServer.create(server);
            CraftServer craftServer = new CraftServer(hybridServer, server);
            hybridServer.setCraftServer(craftServer);
            BukkitRegistry.registerAll(server.registryAccess());
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            System.out.println("[Hybrid] Server started successfully");
        });
    }
}
