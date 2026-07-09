package io.moonspirit.hybrid.mixin.core.server;

import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.moonspirit.hybrid.bukkit.impl.event.EventBridge;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {

    @Shadow
    public ServerPlayer player;

    private double bridge$fromX;
    private double bridge$fromY;
    private double bridge$fromZ;
    private float bridge$fromYaw;
    private float bridge$fromPitch;

    @Inject(method = "handleMovePlayer", at = @At("HEAD"))
    private void onPlayerMovePre(ServerboundMovePlayerPacket packet, CallbackInfo ci) {
        bridge$fromX = player.getX();
        bridge$fromY = player.getY();
        bridge$fromZ = player.getZ();
        bridge$fromYaw = player.getYRot();
        bridge$fromPitch = player.getXRot();
    }

    @Inject(method = "handleMovePlayer", at = @At("RETURN"))
    private void onPlayerMovePost(ServerboundMovePlayerPacket packet, CallbackInfo ci) {
        EventBridge.onPlayerMove(player, bridge$fromX, bridge$fromY, bridge$fromZ, bridge$fromYaw, bridge$fromPitch);
    }
}
