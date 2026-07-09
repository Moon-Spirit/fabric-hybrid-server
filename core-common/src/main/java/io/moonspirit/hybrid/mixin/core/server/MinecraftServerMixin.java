package io.moonspirit.hybrid.mixin.core.server;

import java.util.function.BooleanSupplier;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Server;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.moonspirit.hybrid.bridge.core.server.MinecraftServerBridge;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin implements MinecraftServerBridge {

    private Server bridge$craftServer;

    @Shadow
    public abstract int getTickCount();

    @Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;createLevels(Lnet/minecraft/server/level/progress/WorldLoadListener;)V", shift = At.Shift.AFTER))
    private void onServerInit(CallbackInfo ci) {
        System.out.println("[Hybrid] MinecraftServer initializing...");
    }

    @Inject(method = "tickServer", at = @At("HEAD"))
    private void onTickBegin(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
    }

    @Inject(method = "tickServer", at = @At("RETURN"))
    private void onTickEnd(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
    }

    @Override
    public void bridge$setCraftServer(Server server) {
        this.bridge$craftServer = server;
    }

    @Override
    public Server bridge$getCraftServer() {
        return this.bridge$craftServer;
    }

    @Override
    public void bridge$queuedProcess(Runnable runnable) {
    }

    @Override
    public Commands bridge$getVanillaCommands() {
        return null;
    }
}
