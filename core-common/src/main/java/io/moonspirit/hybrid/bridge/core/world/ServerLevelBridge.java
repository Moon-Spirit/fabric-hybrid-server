package io.moonspirit.hybrid.bridge.core.world;

import org.bukkit.World;

public interface ServerLevelBridge {
    World bridge$getWorld();
    void bridge$setWorld(World world);
}
