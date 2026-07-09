package io.moonspirit.hybrid.mixin.core.server;

import java.util.List;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.moonspirit.hybrid.bridge.core.server.PlayerListBridge;
import io.moonspirit.hybrid.bukkit.impl.event.EventBridge;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin implements PlayerListBridge {

    @Shadow
    public abstract int getPlayerCount();

    @Shadow
    public abstract int getMaxPlayers();

    @Inject(method = "placeNewPlayer", at = @At("RETURN"))
    private void onPlayerJoin(Connection connection, ServerPlayer player, CallbackInfo ci) {
        EventBridge.onPlayerJoin(player);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void onPlayerLeave(ServerPlayer player, CallbackInfo ci) {
    }

    @Override
    public List<ServerPlayer> bridge$getPlayers() {
        return null;
    }

    @Override
    public int bridge$getPlayerCount() {
        return this.getPlayerCount();
    }

    @Override
    public int bridge$getMaxPlayers() {
        return this.getMaxPlayers();
    }
}
