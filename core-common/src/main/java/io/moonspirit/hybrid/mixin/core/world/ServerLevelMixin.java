package io.moonspirit.hybrid.mixin.core.world;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.moonspirit.hybrid.bridge.core.world.ServerLevelBridge;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin implements ServerLevelBridge {

    private World bridge$world;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onWorldTick(CallbackInfo ci) {
    }

    @Override
    public World bridge$getWorld() {
        return this.bridge$world;
    }

    @Override
    public void bridge$setWorld(World world) {
        this.bridge$world = world;
    }
}
