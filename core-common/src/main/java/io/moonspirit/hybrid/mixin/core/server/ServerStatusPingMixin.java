package io.moonspirit.hybrid.mixin.core.server;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.status.ServerboundStatusRequestPacket;
import net.minecraft.server.network.ServerStatusPacketListenerImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.moonspirit.hybrid.bukkit.impl.event.EventBridge;
import io.moonspirit.hybrid.mod.server.HybridServer;

@Mixin(ServerStatusPacketListenerImpl.class)
public class ServerStatusPingMixin {

    @Shadow
    @Final
    private Connection connection;

    @Inject(method = "handleStatusRequest", at = @At("HEAD"))
    private void onHandleStatusRequest(ServerboundStatusRequestPacket packet, CallbackInfo ci) {
        EventBridge.onServerListPing(HybridServer.get().getMinecraftServer(), connection.getRemoteAddress());
    }
}
