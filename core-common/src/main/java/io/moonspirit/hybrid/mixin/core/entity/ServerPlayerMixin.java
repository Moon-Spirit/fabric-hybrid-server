package io.moonspirit.hybrid.mixin.core.entity;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import io.moonspirit.hybrid.bridge.core.entity.player.ServerPlayerBridge;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin implements ServerPlayerBridge {

    @Override
    public org.bukkit.entity.Player bridge$getBukkitPlayer() {
        return null;
    }

    @Override
    public String bridge$getLocale() {
        return null;
    }
}
