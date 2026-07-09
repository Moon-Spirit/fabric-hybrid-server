package io.moonspirit.hybrid.mod.server;

import io.moonspirit.hybrid.bukkit.impl.CraftServer;
import net.minecraft.server.MinecraftServer;

public class HybridServer {
    private static HybridServer INSTANCE;
    private final MinecraftServer server;
    private CraftServer craftServer;

    private HybridServer(MinecraftServer server) {
        this.server = server;
    }

    public static HybridServer create(MinecraftServer server) {
        if (INSTANCE == null) {
            INSTANCE = new HybridServer(server);
        }
        return INSTANCE;
    }

    public static HybridServer get() {
        return INSTANCE;
    }

    public CraftServer getCraftServer() { return craftServer; }
    public void setCraftServer(CraftServer craftServer) { this.craftServer = craftServer; }
    public MinecraftServer getMinecraftServer() { return server; }
}
