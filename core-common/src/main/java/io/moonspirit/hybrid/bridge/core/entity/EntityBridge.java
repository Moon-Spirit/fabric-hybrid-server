package io.moonspirit.hybrid.bridge.core.entity;

public interface EntityBridge {
    org.bukkit.entity.Entity bridge$getBukkitEntity();
    void bridge$setBukkitEntity(org.bukkit.entity.Entity entity);
}
