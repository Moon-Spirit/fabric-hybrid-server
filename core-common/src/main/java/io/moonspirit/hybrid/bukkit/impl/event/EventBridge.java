package io.moonspirit.hybrid.bukkit.impl.event;

import io.moonspirit.hybrid.bukkit.impl.CraftPlayer;
import io.moonspirit.hybrid.bukkit.impl.CraftServer;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftLivingEntity;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftProjectile;
import io.moonspirit.hybrid.mod.server.HybridServer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EventBridge {

    public static void onPlayerJoin(ServerPlayer player) {
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        PlayerJoinEvent event = new PlayerJoinEvent(craftPlayer, "§e" + player.getScoreboardName() + " joined the game");
        craftServer.getPluginManager().callEvent(event);

        if (event.getJoinMessage() != null) {
            craftServer.broadcastMessage(event.getJoinMessage());
        }
    }

    public static void onBlockBreak(ServerLevel level, BlockPos pos, ServerPlayer player) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        org.bukkit.block.Block block = craftServer.getWorld(level.dimension().location().toString()).getBlockAt(pos.getX(), pos.getY(), pos.getZ());
        BlockBreakEvent event = new BlockBreakEvent(block, craftPlayer);
        craftServer.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            // 如果需要取消，需要在 Mixin 中处理
        }
    }

    public static EntityDeathEvent onEntityDeath(LivingEntity entity, DamageSource source) {
        CraftServer craftServer = HybridServer.get().getCraftServer();
        org.bukkit.entity.LivingEntity bukkitEntity = null;
        if (entity instanceof ServerPlayer serverPlayer) {
            bukkitEntity = new CraftPlayer(serverPlayer);
        }
        if (bukkitEntity == null) return null;

        List<ItemStack> drops = new ArrayList<>();
        EntityDeathEvent event = new EntityDeathEvent(bukkitEntity, drops, 0);
        craftServer.getPluginManager().callEvent(event);
        return event;
    }

    public static void onBlockPlace(ServerLevel level, BlockPos pos, ServerPlayer player, net.minecraft.world.item.ItemStack stack) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        org.bukkit.block.Block block = craftServer.getWorld(level.dimension().location().toString()).getBlockAt(pos.getX(), pos.getY(), pos.getZ());
        BlockPlaceEvent event = new BlockPlaceEvent(block, block != null ? block.getState() : null, null, null, craftPlayer, true, EquipmentSlot.HAND);
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onPlayerInteract(ServerPlayer player, InteractionHand hand) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
        PlayerInteractEvent event = new PlayerInteractEvent(craftPlayer, org.bukkit.event.block.Action.RIGHT_CLICK_AIR, null, null, null, slot);
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onEntityDamage(LivingEntity entity, DamageSource source, float amount) {
        if (entity == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftLivingEntity craftEntity = new CraftLivingEntity(entity);
        EntityDamageEvent event = new EntityDamageEvent(craftEntity, DamageCause.ENTITY_ATTACK, amount);
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onPlayerMove(ServerPlayer player, double fromX, double fromY, double fromZ, float fromYaw, float fromPitch) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        Location from = new Location(craftPlayer.getWorld(), fromX, fromY, fromZ, fromYaw, fromPitch);
        Location to = craftPlayer.getLocation();
        PlayerMoveEvent event = new PlayerMoveEvent(craftPlayer, from, to);
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onServerListPing(MinecraftServer server, SocketAddress address) {
        CraftServer craftServer = HybridServer.get().getCraftServer();
        InetSocketAddress socketAddress = (InetSocketAddress) address;
        InetAddress inetAddress = socketAddress.getAddress();
        String hostname = socketAddress.getHostString();
        ServerListPingEvent event = new ServerListPingEvent(
            hostname,
            inetAddress,
            server.getMotd(),
            server.getPlayerCount(),
            server.getMaxPlayers()
        );
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onPlayerChat(ServerPlayer player, String message) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(true, craftPlayer, message, new HashSet<>(craftServer.getOnlinePlayers()));
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onPlayerCommand(ServerPlayer player, String command) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(craftPlayer, command);
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onInventoryClick(ServerPlayer player, int slot, InventoryAction action, ClickType clickType, InventoryType.SlotType slotType, InventoryView view, ItemStack currentItem, ItemStack cursorItem) {
        if (player == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftPlayer craftPlayer = new CraftPlayer(player);
        InventoryClickEvent event = new InventoryClickEvent(view, slotType, slot, clickType, action);
        craftServer.getPluginManager().callEvent(event);
    }

    public static void onProjectileHit(Projectile projectile, org.bukkit.block.Block hitBlock, org.bukkit.entity.Entity hitEntity) {
        if (projectile == null) return;
        CraftServer craftServer = HybridServer.get().getCraftServer();
        CraftProjectile craftProjectile = new CraftProjectile(projectile);
        ProjectileHitEvent event = new ProjectileHitEvent(craftProjectile, hitEntity, hitBlock);
        craftServer.getPluginManager().callEvent(event);
    }

}
