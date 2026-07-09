package io.moonspirit.hybrid.bridge.core.world.level;

import org.bukkit.Chunk;

public interface ServerLevelChunkBridge {
    Chunk bridge$getChunk();
    void bridge$setChunk(Chunk chunk);
}
