package io.moonspirit.hybrid.mixin.core.entity;

import io.moonspirit.hybrid.bridge.core.entity.EntityBridge;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftEntity;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftLivingEntity;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityBridge {

    private org.bukkit.entity.Entity bridge$bukkitEntity;

    @Override
    public org.bukkit.entity.Entity bridge$getBukkitEntity() {
        if (bridge$bukkitEntity == null) {
            bridge$bukkitEntity = createBukkitEntity();
        }
        return bridge$bukkitEntity;
    }

    @Override
    public void bridge$setBukkitEntity(org.bukkit.entity.Entity entity) {
        this.bridge$bukkitEntity = entity;
    }

    private org.bukkit.entity.Entity createBukkitEntity() {
        Entity self = (Entity) (Object) this;
        if (self instanceof ServerPlayer) {
            return new io.moonspirit.hybrid.bukkit.impl.CraftPlayer((ServerPlayer) self);
        }
        if (self instanceof Mob) {
            return new CraftMob((Mob) self);
        }
        if (self instanceof LivingEntity) {
            return new CraftLivingEntity((LivingEntity) self);
        }
        return new CraftEntity(self);
    }
}
