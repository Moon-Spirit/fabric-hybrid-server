package io.moonspirit.hybrid.bridge.core.world.item;

public interface ItemStackBridge {
    org.bukkit.inventory.ItemStack bridge$getBukkitStack();
    void bridge$setBukkitStack(org.bukkit.inventory.ItemStack stack);
}
