package io.moonspirit.hybrid.bridge.core.server;

import java.util.List;
import net.minecraft.server.level.ServerPlayer;

public interface PlayerListBridge {
    List<ServerPlayer> bridge$getPlayers();
    int bridge$getPlayerCount();
    int bridge$getMaxPlayers();
}
