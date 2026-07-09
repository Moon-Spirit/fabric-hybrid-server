package io.moonspirit.hybrid.bukkit.impl;

import io.moonspirit.hybrid.mod.server.HybridServer;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.bukkit.WorldBorder;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.*;


public class CraftPlayer implements Player {
    private final ServerPlayer handle;

    public CraftPlayer(ServerPlayer handle) {
        this.handle = handle;
    }

    public ServerPlayer getHandle() { return handle; }

    // ===== Core method implementations =====
    @Override public AttributeInstance getAttribute(Attribute p0) { return null; }
    @Override public String getDisplayName() { return handle.getScoreboardName(); }
    @Override public int getEntityId() { return handle.getId(); }
    @Override public double getEyeHeight() { return 1.62; }
    @Override public double getEyeHeight(boolean ignorePose) { return 1.62; }
    @Override public float getFlySpeed() { return 0.1f; }
    @Override public int getFoodLevel() { return 20; }
    @Override public GameMode getGameMode() { return GameMode.SURVIVAL; }
    @Override public double getHealth() { return 20.0; }
    @Override public double getHealthScale() { return 20.0; }
    @Override public double getHeight() { return 1.8; }
    @Override public String getLocale() { return "en_US"; }
    @Override public MainHand getMainHand() { return MainHand.RIGHT; }
    @Override public double getMaxHealth() { return 20.0; }
    @Override public int getMaximumAir() { return 300; }
    @Override public int getMaximumNoDamageTicks() { return 20; }
    @Override public String getName() { return handle.getScoreboardName(); }
    @Override public PistonMoveReaction getPistonMoveReaction() { return PistonMoveReaction.MOVE; }
    @Override public Player getPlayer() { return this; }
    @Override public String getPlayerListName() { return handle.getScoreboardName(); }
    @Override public Pose getPose() { return Pose.STANDING; }
    @Override public int getRemainingAir() { return 300; }
    @Override public Server getServer() { return HybridServer.get().getCraftServer(); }
    @Override public SpawnCategory getSpawnCategory() { return SpawnCategory.MISC; }
    @Override public EntityType getType() { return EntityType.PLAYER; }
    @Override public UUID getUniqueId() { return handle.getUUID(); }
    @Override public float getWalkSpeed() { return 0.2f; }
    @Override public double getWidth() { return 0.6; }
    @Override public boolean hasAI() { return true; }
    @Override public boolean hasGravity() { return true; }
    @Override public boolean hasPermission(Permission perm) { return true; }
    @Override public boolean hasPermission(String name) { return true; }
    @Override public boolean isBanned() { return false; }
    @Override public boolean isCollidable() { return true; }
    @Override public boolean isConversing() { return false; }
    @Override public boolean isDead() { return false; }
    @Override public boolean isOnline() { return true; }
    @Override public boolean isOp() { return handle.server != null && handle.server.getPlayerList().isOp(handle.getGameProfile()); }
    @Override public boolean isPermissionSet(Permission p0) { return false; }
    @Override public boolean isPermissionSet(String p0) { return false; }
    @Override public boolean isPersistent() { return true; }
    @Override public boolean isSleepingIgnored() { return false; }
    @Override public boolean isValid() { return true; }
    @Override public boolean isVisibleByDefault() { return true; }
    @Override public boolean isWhitelisted() { return false; }
    @Override public void sendMessage(String message) {
        if (message != null && !message.isEmpty()) {
            handle.sendSystemMessage(net.minecraft.network.chat.Component.literal(message));
        }
    }
    @Override public void sendMessage(String... messages) { for (String m : messages) sendMessage(m); }
    @Override public void sendMessage(UUID uuid, String message) { sendMessage(message); }
    @Override public void sendMessage(UUID uuid, String... messages) { sendMessage(messages); }
    @Override public void sendRawMessage(String message) { sendMessage(message); }
    @Override public void sendRawMessage(UUID uuid, String message) { sendMessage(message); }
    @Override public void setOp(boolean p0) {}
    @Override public void setSleepingIgnored(boolean p0) {}
    @Override public void setWhitelisted(boolean p0) {}
    @Override public Player.Spigot spigot() { return null; }

    // ===== Generated stubs =====
    @Override public void abandonConversation(org.bukkit.conversations.Conversation p0) {}
    @Override public void abandonConversation(org.bukkit.conversations.Conversation p0, org.bukkit.conversations.ConversationAbandonedEvent p1) {}
    @Override public void acceptConversationInput(java.lang.String p0) {}
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin p0) { return null; }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin p0, java.lang.String p1, boolean p2) { return null; }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin p0, java.lang.String p1, boolean p2, int p3) { return null; }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin p0, int p1) { return null; }
    @Override public void addCustomChatCompletions(java.util.Collection<java.lang.String> p0) {}
    @Override public boolean addPassenger(org.bukkit.entity.Entity p0) { return false; }
    @Override public boolean addPotionEffect(org.bukkit.potion.PotionEffect p0) { return false; }
    @Override public boolean addPotionEffect(org.bukkit.potion.PotionEffect p0, boolean p1) { return false; }
    @Override public boolean addPotionEffects(java.util.Collection<org.bukkit.potion.PotionEffect> p0) { return false; }
    @Override public boolean addScoreboardTag(java.lang.String p0) { return false; }
    @Override public void attack(org.bukkit.entity.Entity p0) {}
    @Override public org.bukkit.BanEntry<org.bukkit.profile.PlayerProfile> ban(java.lang.String p0, java.util.Date p1, java.lang.String p2) { return null; }
    @Override public org.bukkit.BanEntry<org.bukkit.profile.PlayerProfile> ban(java.lang.String p0, java.util.Date p1, java.lang.String p2, boolean p3) { return null; }
    @Override public org.bukkit.BanEntry<org.bukkit.profile.PlayerProfile> ban(java.lang.String p0, java.time.Duration p1, java.lang.String p2) { return null; }
    @Override public org.bukkit.BanEntry<org.bukkit.profile.PlayerProfile> ban(java.lang.String p0, java.time.Duration p1, java.lang.String p2, boolean p3) { return null; }
    @Override public org.bukkit.BanEntry<org.bukkit.profile.PlayerProfile> ban(java.lang.String p0, java.time.Instant p1, java.lang.String p2) { return null; }
    @Override public org.bukkit.BanEntry<org.bukkit.profile.PlayerProfile> ban(java.lang.String p0, java.time.Instant p1, java.lang.String p2, boolean p3) { return null; }
    @Override public org.bukkit.BanEntry<java.net.InetAddress> banIp(java.lang.String p0, java.util.Date p1, java.lang.String p2, boolean p3) { return null; }
    @Override public org.bukkit.BanEntry<java.net.InetAddress> banIp(java.lang.String p0, java.time.Duration p1, java.lang.String p2, boolean p3) { return null; }
    @Override public org.bukkit.BanEntry<java.net.InetAddress> banIp(java.lang.String p0, java.time.Instant p1, java.lang.String p2, boolean p3) { return null; }
    @Override public boolean beginConversation(org.bukkit.conversations.Conversation p0) { return false; }
    @Override public boolean breakBlock(org.bukkit.block.Block p0) { return false; }
    @Override public boolean canBreatheUnderwater() { return false; }
    @Override public boolean canSee(org.bukkit.entity.Entity p0) { return false; }
    @Override public boolean canSee(org.bukkit.entity.Player p0) { return false; }
    @Override public void chat(java.lang.String p0) {}
    @Override public void closeInventory() {}
    @Override public void damage(double p0) {}
    @Override public void damage(double p0, org.bukkit.entity.Entity p1) {}
    @Override public void decrementStatistic(org.bukkit.Statistic p0) {}
    @Override public void decrementStatistic(org.bukkit.Statistic p0, org.bukkit.entity.EntityType p1) {}
    @Override public void decrementStatistic(org.bukkit.Statistic p0, org.bukkit.entity.EntityType p1, int p2) {}
    @Override public void decrementStatistic(org.bukkit.Statistic p0, org.bukkit.Material p1) {}
    @Override public void decrementStatistic(org.bukkit.Statistic p0, org.bukkit.Material p1, int p2) {}
    @Override public void decrementStatistic(org.bukkit.Statistic p0, int p1) {}
    @Override public boolean discoverRecipe(org.bukkit.NamespacedKey p0) { return false; }
    @Override public int discoverRecipes(java.util.Collection<org.bukkit.NamespacedKey> p0) { return 0; }
    @Override public boolean dropItem(boolean p0) { return false; }
    @Override public boolean eject() { return false; }
    @Override public org.bukkit.entity.Firework fireworkBoost(org.bukkit.inventory.ItemStack p0) { return null; }
    @Override public double getAbsorptionAmount() { return 0.0; }
    @Override public java.util.Collection<org.bukkit.potion.PotionEffect> getActivePotionEffects() { return null; }
    @Override public java.net.InetSocketAddress getAddress() { return null; }
    @Override public org.bukkit.advancement.AdvancementProgress getAdvancementProgress(org.bukkit.advancement.Advancement p0) { return null; }
    @Override public boolean getAllowFlight() { return false; }
    @Override public int getArrowCooldown() { return 0; }
    @Override public int getArrowsInBody() { return 0; }
    @Override public float getAttackCooldown() { return 0.0f; }
    @Override public org.bukkit.Location getBedLocation() { return null; }
    @Override public org.bukkit.Location getBedSpawnLocation() { return null; }
    @Override public org.bukkit.util.BoundingBox getBoundingBox() { return null; }
    @Override public boolean getCanPickupItems() { return false; }
    @Override public org.bukkit.entity.EntityCategory getCategory() { return null; }
    @Override public int getClientViewDistance() { return 0; }
    @Override public java.util.Set<java.util.UUID> getCollidableExemptions() { return null; }
    @Override public org.bukkit.Location getCompassTarget() { return null; }
    @Override public int getCooldown(org.bukkit.Material p0) { return 0; }
    @Override public java.lang.String getCustomName() { return null; }
    @Override public org.bukkit.Sound getDeathSound() { return null; }
    @Override public java.util.Set<org.bukkit.NamespacedKey> getDiscoveredRecipes() { return null; }
    @Override public org.bukkit.Sound getDrinkingSound(org.bukkit.inventory.ItemStack p0) { return null; }
    @Override public org.bukkit.Sound getEatingSound(org.bukkit.inventory.ItemStack p0) { return null; }
    @Override public java.util.Set<org.bukkit.permissions.PermissionAttachmentInfo> getEffectivePermissions() { return null; }
    @Override public int getEnchantmentSeed() { return 0; }
    @Override public org.bukkit.inventory.Inventory getEnderChest() { return null; }
    @Override public org.bukkit.inventory.EntityEquipment getEquipment() { return null; }
    @Override public float getExhaustion() { return 0.0f; }
    @Override public float getExp() { return 0.0f; }
    @Override public int getExpCooldown() { return 0; }
    @Override public int getExpToLevel() { return 0; }
    @Override public org.bukkit.Location getEyeLocation() { return null; }
    @Override public org.bukkit.block.BlockFace getFacing() { return null; }
    @Override public org.bukkit.Sound getFallDamageSound(int p0) { return null; }
    @Override public org.bukkit.Sound getFallDamageSoundBig() { return null; }
    @Override public org.bukkit.Sound getFallDamageSoundSmall() { return null; }
    @Override public float getFallDistance() { return 0.0f; }
    @Override public int getFireTicks() { return 0; }
    @Override public long getFirstPlayed() { return 0L; }
    @Override public int getFreezeTicks() { return 0; }
    @Override public org.bukkit.Sound getHurtSound() { return null; }
    @Override public org.bukkit.inventory.PlayerInventory getInventory() { return null; }
    @Override public org.bukkit.inventory.ItemStack getItemInHand() { return null; }
    @Override public org.bukkit.inventory.ItemStack getItemInUse() { return null; }
    @Override public org.bukkit.inventory.ItemStack getItemOnCursor() { return null; }
    @Override public org.bukkit.entity.Player getKiller() { return null; }
    @Override public double getLastDamage() { return 0.0; }
    @Override public org.bukkit.event.entity.EntityDamageEvent getLastDamageCause() { return null; }
    @Override public org.bukkit.Location getLastDeathLocation() { return null; }
    @Override public long getLastPlayed() { return 0L; }
    @Override public java.util.List<org.bukkit.block.Block> getLastTwoTargetBlocks(java.util.Set<org.bukkit.Material> p0, int p1) { return null; }
    @Override public org.bukkit.entity.Entity getLeashHolder() { return null; }
    @Override public int getLevel() { return 0; }
    @Override public java.util.List<org.bukkit.block.Block> getLineOfSight(java.util.Set<org.bukkit.Material> p0, int p1) { return null; }
    @Override public java.util.Set<java.lang.String> getListeningPluginChannels() { return null; }
    @Override public org.bukkit.Location getLocation() { return null; }
    @Override public org.bukkit.Location getLocation(org.bukkit.Location p0) { return null; }
    @Override public int getMaxFireTicks() { return 0; }
    @Override public int getMaxFreezeTicks() { return 0; }
    @Override public <T> T getMemory(org.bukkit.entity.memory.MemoryKey<T> p0) { return null; }
    @Override public java.util.List<org.bukkit.metadata.MetadataValue> getMetadata(java.lang.String p0) { return null; }
    @Override public java.util.List<org.bukkit.entity.Entity> getNearbyEntities(double p0, double p1, double p2) { return null; }
    @Override public int getNoActionTicks() { return 0; }
    @Override public int getNoDamageTicks() { return 0; }
    @Override public org.bukkit.inventory.InventoryView getOpenInventory() { return null; }
    @Override public org.bukkit.entity.Entity getPassenger() { return null; }
    @Override public java.util.List<org.bukkit.entity.Entity> getPassengers() { return null; }
    @Override public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() { return null; }
    @Override public int getPing() { return 0; }
    @Override public java.lang.String getPlayerListFooter() { return null; }
    @Override public java.lang.String getPlayerListHeader() { return null; }
    @Override public org.bukkit.profile.PlayerProfile getPlayerProfile() { return null; }
    @Override public long getPlayerTime() { return 0L; }
    @Override public long getPlayerTimeOffset() { return 0L; }
    @Override public org.bukkit.WeatherType getPlayerWeather() { return null; }
    @Override public int getPortalCooldown() { return 0; }
    @Override public org.bukkit.potion.PotionEffect getPotionEffect(org.bukkit.potion.PotionEffectType p0) { return null; }
    @Override public org.bukkit.GameMode getPreviousGameMode() { return null; }
    @Override public boolean getRemoveWhenFarAway() { return false; }
    @Override public int getSaturatedRegenRate() { return 0; }
    @Override public float getSaturation() { return 0.0f; }
    @Override public org.bukkit.scoreboard.Scoreboard getScoreboard() { return null; }
    @Override public java.util.Set<java.lang.String> getScoreboardTags() { return null; }
    @Override public org.bukkit.entity.Entity getShoulderEntityLeft() { return null; }
    @Override public org.bukkit.entity.Entity getShoulderEntityRight() { return null; }
    @Override public int getSleepTicks() { return 0; }
    @Override public org.bukkit.entity.Entity getSpectatorTarget() { return null; }
    @Override public int getStarvationRate() { return 0; }
    @Override public int getStatistic(org.bukkit.Statistic p0) { return 0; }
    @Override public int getStatistic(org.bukkit.Statistic p0, org.bukkit.entity.EntityType p1) { return 0; }
    @Override public int getStatistic(org.bukkit.Statistic p0, org.bukkit.Material p1) { return 0; }
    @Override public org.bukkit.Sound getSwimHighSpeedSplashSound() { return null; }
    @Override public org.bukkit.Sound getSwimSound() { return null; }
    @Override public org.bukkit.Sound getSwimSplashSound() { return null; }
    @Override public org.bukkit.block.Block getTargetBlock(java.util.Set<org.bukkit.Material> p0, int p1) { return null; }
    @Override public org.bukkit.block.Block getTargetBlockExact(int p0) { return null; }
    @Override public org.bukkit.block.Block getTargetBlockExact(int p0, org.bukkit.FluidCollisionMode p1) { return null; }
    @Override public int getTicksLived() { return 0; }
    @Override public int getTotalExperience() { return 0; }
    @Override public int getUnsaturatedRegenRate() { return 0; }
    @Override public org.bukkit.entity.Entity getVehicle() { return null; }
    @Override public org.bukkit.util.Vector getVelocity() { return null; }
    @Override public org.bukkit.World getWorld() { return null; }
    @Override public org.bukkit.WorldBorder getWorldBorder() { return null; }
    @Override public void giveExp(int p0) {}
    @Override public void giveExpLevels(int p0) {}
    @Override public boolean hasCooldown(org.bukkit.Material p0) { return false; }
    @Override public boolean hasDiscoveredRecipe(org.bukkit.NamespacedKey p0) { return false; }
    @Override public boolean hasLineOfSight(org.bukkit.entity.Entity p0) { return false; }
    @Override public boolean hasMetadata(java.lang.String p0) { return false; }
    @Override public boolean hasPlayedBefore() { return false; }
    @Override public boolean hasPotionEffect(org.bukkit.potion.PotionEffectType p0) { return false; }
    @Override public void hideEntity(org.bukkit.plugin.Plugin p0, org.bukkit.entity.Entity p1) {}
    @Override public void hidePlayer(org.bukkit.entity.Player p0) {}
    @Override public void hidePlayer(org.bukkit.plugin.Plugin p0, org.bukkit.entity.Player p1) {}
    @Override public void incrementStatistic(org.bukkit.Statistic p0) {}
    @Override public void incrementStatistic(org.bukkit.Statistic p0, org.bukkit.entity.EntityType p1) {}
    @Override public void incrementStatistic(org.bukkit.Statistic p0, org.bukkit.entity.EntityType p1, int p2) {}
    @Override public void incrementStatistic(org.bukkit.Statistic p0, org.bukkit.Material p1) {}
    @Override public void incrementStatistic(org.bukkit.Statistic p0, org.bukkit.Material p1, int p2) {}
    @Override public void incrementStatistic(org.bukkit.Statistic p0, int p1) {}
    @Override public boolean isAllowingServerListings() { return false; }
    @Override public boolean isBlocking() { return false; }
    @Override public boolean isClimbing() { return false; }
    @Override public boolean isCustomNameVisible() { return false; }
    @Override public boolean isEmpty() { return false; }
    @Override public boolean isFlying() { return false; }
    @Override public boolean isFrozen() { return false; }
    @Override public boolean isGliding() { return false; }
    @Override public boolean isGlowing() { return false; }
    @Override public boolean isHandRaised() { return false; }
    @Override public boolean isHealthScaled() { return false; }
    @Override public boolean isInWater() { return false; }
    @Override public boolean isInsideVehicle() { return false; }
    @Override public boolean isInvisible() { return false; }
    @Override public boolean isInvulnerable() { return false; }
    @Override public boolean isLeashed() { return false; }
    @Override public boolean isOnGround() { return false; }
    @Override public boolean isPlayerTimeRelative() { return false; }
    @Override public boolean isRiptiding() { return false; }
    @Override public boolean isSilent() { return false; }
    @Override public boolean isSleeping() { return false; }
    @Override public boolean isSneaking() { return false; }
    @Override public boolean isSprinting() { return false; }
    @Override public boolean isSwimming() { return false; }
    @Override public boolean isVisualFire() { return false; }
    @Override public void kickPlayer(java.lang.String p0) {}
    @Override public <T extends org.bukkit.entity.Projectile> T launchProjectile(java.lang.Class<? extends T> p0) { return null; }
    @Override public <T extends org.bukkit.entity.Projectile> T launchProjectile(java.lang.Class<? extends T> p0, org.bukkit.util.Vector p1) { return null; }
    @Override public boolean leaveVehicle() { return false; }
    @Override public void loadData() {}
    @Override public void openBook(org.bukkit.inventory.ItemStack p0) {}
    @Override public org.bukkit.inventory.InventoryView openEnchanting(org.bukkit.Location p0, boolean p1) { return null; }
    @Override public org.bukkit.inventory.InventoryView openInventory(org.bukkit.inventory.Inventory p0) { return null; }
    @Override public void openInventory(org.bukkit.inventory.InventoryView p0) {}
    @Override public org.bukkit.inventory.InventoryView openMerchant(org.bukkit.inventory.Merchant p0, boolean p1) { return null; }
    @Override public org.bukkit.inventory.InventoryView openMerchant(org.bukkit.entity.Villager p0, boolean p1) { return null; }
    @Override public void openSign(org.bukkit.block.Sign p0) {}
    @Override public void openSign(org.bukkit.block.Sign p0, org.bukkit.block.sign.Side p1) {}
    @Override public org.bukkit.inventory.InventoryView openWorkbench(org.bukkit.Location p0, boolean p1) { return null; }
    @Override public boolean performCommand(java.lang.String p0) { return false; }
    @Override public void playEffect(org.bukkit.EntityEffect p0) {}
    @Override public <T> void playEffect(org.bukkit.Location p0, org.bukkit.Effect p1, T p2) {}
    @Override public void playEffect(org.bukkit.Location p0, org.bukkit.Effect p1, int p2) {}
    @Override public void playHurtAnimation(float p0) {}
    @Override public void playNote(org.bukkit.Location p0, org.bukkit.Instrument p1, org.bukkit.Note p2) {}
    @Override public void playNote(org.bukkit.Location p0, byte p1, byte p2) {}
    @Override public void playSound(org.bukkit.entity.Entity p0, org.bukkit.Sound p1, org.bukkit.SoundCategory p2, float p3, float p4) {}
    @Override public void playSound(org.bukkit.entity.Entity p0, org.bukkit.Sound p1, float p2, float p3) {}
    @Override public void playSound(org.bukkit.entity.Entity p0, java.lang.String p1, org.bukkit.SoundCategory p2, float p3, float p4) {}
    @Override public void playSound(org.bukkit.entity.Entity p0, java.lang.String p1, float p2, float p3) {}
    @Override public void playSound(org.bukkit.Location p0, org.bukkit.Sound p1, org.bukkit.SoundCategory p2, float p3, float p4) {}
    @Override public void playSound(org.bukkit.Location p0, org.bukkit.Sound p1, float p2, float p3) {}
    @Override public void playSound(org.bukkit.Location p0, java.lang.String p1, org.bukkit.SoundCategory p2, float p3, float p4) {}
    @Override public void playSound(org.bukkit.Location p0, java.lang.String p1, float p2, float p3) {}
    @Override public org.bukkit.util.RayTraceResult rayTraceBlocks(double p0) { return null; }
    @Override public org.bukkit.util.RayTraceResult rayTraceBlocks(double p0, org.bukkit.FluidCollisionMode p1) { return null; }
    @Override public void recalculatePermissions() {}
    @Override public void remove() {}
    @Override public void removeAttachment(org.bukkit.permissions.PermissionAttachment p0) {}
    @Override public void removeCustomChatCompletions(java.util.Collection<java.lang.String> p0) {}
    @Override public void removeMetadata(java.lang.String p0, org.bukkit.plugin.Plugin p1) {}
    @Override public boolean removePassenger(org.bukkit.entity.Entity p0) { return false; }
    @Override public void removePotionEffect(org.bukkit.potion.PotionEffectType p0) {}
    @Override public boolean removeScoreboardTag(java.lang.String p0) { return false; }
    @Override public void resetMaxHealth() {}
    @Override public void resetPlayerTime() {}
    @Override public void resetPlayerWeather() {}
    @Override public void resetTitle() {}
    @Override public void saveData() {}
    @Override public void sendBlockChange(org.bukkit.Location p0, org.bukkit.block.data.BlockData p1) {}
    @Override public void sendBlockChange(org.bukkit.Location p0, org.bukkit.Material p1, byte p2) {}
    @Override public void sendBlockChanges(java.util.Collection<org.bukkit.block.BlockState> p0) {}
    @Override public void sendBlockChanges(java.util.Collection<org.bukkit.block.BlockState> p0, boolean p1) {}
    @Override public void sendBlockDamage(org.bukkit.Location p0, float p1) {}
    @Override public void sendBlockDamage(org.bukkit.Location p0, float p1, org.bukkit.entity.Entity p2) {}
    @Override public void sendBlockDamage(org.bukkit.Location p0, float p1, int p2) {}
    @Override public void sendBlockUpdate(org.bukkit.Location p0, org.bukkit.block.TileState p1) {}
    @Override public void sendEquipmentChange(org.bukkit.entity.LivingEntity p0, org.bukkit.inventory.EquipmentSlot p1, org.bukkit.inventory.ItemStack p2) {}
    @Override public void sendEquipmentChange(org.bukkit.entity.LivingEntity p0, java.util.Map<org.bukkit.inventory.EquipmentSlot, org.bukkit.inventory.ItemStack> p1) {}
    @Override public void sendExperienceChange(float p0) {}
    @Override public void sendExperienceChange(float p0, int p1) {}
    @Override public void sendHealthUpdate() {}
    @Override public void sendHealthUpdate(double p0, int p1, float p2) {}
    @Override public void sendHurtAnimation(float p0) {}
    @Override public void sendMap(org.bukkit.map.MapView p0) {}
    @Override public void sendPluginMessage(org.bukkit.plugin.Plugin p0, java.lang.String p1, byte[] p2) {}
    @Override public void sendSignChange(org.bukkit.Location p0, java.lang.String[] p1) {}
    @Override public void sendSignChange(org.bukkit.Location p0, java.lang.String[] p1, org.bukkit.DyeColor p2) {}
    @Override public void sendSignChange(org.bukkit.Location p0, java.lang.String[] p1, org.bukkit.DyeColor p2, boolean p3) {}
    @Override public void sendTitle(java.lang.String p0, java.lang.String p1) {}
    @Override public void sendTitle(java.lang.String p0, java.lang.String p1, int p2, int p3, int p4) {}
    @Override public java.util.Map<java.lang.String, java.lang.Object> serialize() { return null; }
    @Override public void setAI(boolean p0) {}
    @Override public void setAbsorptionAmount(double p0) {}
    @Override public void setAllowFlight(boolean p0) {}
    @Override public void setArrowCooldown(int p0) {}
    @Override public void setArrowsInBody(int p0) {}
    @Override public void setBedSpawnLocation(org.bukkit.Location p0) {}
    @Override public void setBedSpawnLocation(org.bukkit.Location p0, boolean p1) {}
    @Override public void setCanPickupItems(boolean p0) {}
    @Override public void setCollidable(boolean p0) {}
    @Override public void setCompassTarget(org.bukkit.Location p0) {}
    @Override public void setCooldown(org.bukkit.Material p0, int p1) {}
    @Override public void setCustomChatCompletions(java.util.Collection<java.lang.String> p0) {}
    @Override public void setCustomName(java.lang.String p0) {}
    @Override public void setCustomNameVisible(boolean p0) {}
    @Override public void setDisplayName(java.lang.String p0) {}
    @Override public void setEnchantmentSeed(int p0) {}
    @Override public void setExhaustion(float p0) {}
    @Override public void setExp(float p0) {}
    @Override public void setExpCooldown(int p0) {}
    @Override public void setFallDistance(float p0) {}
    @Override public void setFireTicks(int p0) {}
    @Override public void setFlySpeed(float p0) {}
    @Override public void setFlying(boolean p0) {}
    @Override public void setFoodLevel(int p0) {}
    @Override public void setFreezeTicks(int p0) {}
    @Override public void setGameMode(org.bukkit.GameMode p0) {}
    @Override public void setGliding(boolean p0) {}
    @Override public void setGlowing(boolean p0) {}
    @Override public void setGravity(boolean p0) {}
    @Override public void setHealth(double p0) {}
    @Override public void setHealthScale(double p0) {}
    @Override public void setHealthScaled(boolean p0) {}
    @Override public void setInvisible(boolean p0) {}
    @Override public void setInvulnerable(boolean p0) {}
    @Override public void setItemInHand(org.bukkit.inventory.ItemStack p0) {}
    @Override public void setItemOnCursor(org.bukkit.inventory.ItemStack p0) {}
    @Override public void setLastDamage(double p0) {}
    @Override public void setLastDamageCause(org.bukkit.event.entity.EntityDamageEvent p0) {}
    @Override public void setLastDeathLocation(org.bukkit.Location p0) {}
    @Override public boolean setLeashHolder(org.bukkit.entity.Entity p0) { return false; }
    @Override public void setLevel(int p0) {}
    @Override public void setMaxHealth(double p0) {}
    @Override public void setMaximumAir(int p0) {}
    @Override public void setMaximumNoDamageTicks(int p0) {}
    @Override public <T> void setMemory(org.bukkit.entity.memory.MemoryKey<T> p0, T p1) {}
    @Override public void setMetadata(java.lang.String p0, org.bukkit.metadata.MetadataValue p1) {}
    @Override public void setNoActionTicks(int p0) {}
    @Override public void setNoDamageTicks(int p0) {}
    @Override public boolean setPassenger(org.bukkit.entity.Entity p0) { return false; }
    @Override public void setPersistent(boolean p0) {}
    @Override public void setPlayerListFooter(java.lang.String p0) {}
    @Override public void setPlayerListHeader(java.lang.String p0) {}
    @Override public void setPlayerListHeaderFooter(java.lang.String p0, java.lang.String p1) {}
    @Override public void setPlayerListName(java.lang.String p0) {}
    @Override public void setPlayerTime(long p0, boolean p1) {}
    @Override public void setPlayerWeather(org.bukkit.WeatherType p0) {}
    @Override public void setPortalCooldown(int p0) {}
    @Override public void setRemainingAir(int p0) {}
    @Override public void setRemoveWhenFarAway(boolean p0) {}
    @Override public void setResourcePack(java.lang.String p0) {}
    @Override public void setResourcePack(java.lang.String p0, byte[] p1) {}
    @Override public void setResourcePack(java.lang.String p0, byte[] p1, java.lang.String p2) {}
    @Override public void setResourcePack(java.lang.String p0, byte[] p1, java.lang.String p2, boolean p3) {}
    @Override public void setResourcePack(java.lang.String p0, byte[] p1, boolean p2) {}
    @Override public void setRotation(float p0, float p1) {}
    @Override public void setSaturatedRegenRate(int p0) {}
    @Override public void setSaturation(float p0) {}
    @Override public void setScoreboard(org.bukkit.scoreboard.Scoreboard p0) {}
    @Override public void setShoulderEntityLeft(org.bukkit.entity.Entity p0) {}
    @Override public void setShoulderEntityRight(org.bukkit.entity.Entity p0) {}
    @Override public void setSilent(boolean p0) {}
    @Override public void setSneaking(boolean p0) {}
    @Override public void setSpectatorTarget(org.bukkit.entity.Entity p0) {}
    @Override public void setSprinting(boolean p0) {}
    @Override public void setStarvationRate(int p0) {}
    @Override public void setStatistic(org.bukkit.Statistic p0, org.bukkit.entity.EntityType p1, int p2) {}
    @Override public void setStatistic(org.bukkit.Statistic p0, org.bukkit.Material p1, int p2) {}
    @Override public void setStatistic(org.bukkit.Statistic p0, int p1) {}
    @Override public void setSwimming(boolean p0) {}
    @Override public void setTexturePack(java.lang.String p0) {}
    @Override public void setTicksLived(int p0) {}
    @Override public void setTotalExperience(int p0) {}
    @Override public void setUnsaturatedRegenRate(int p0) {}
    @Override public void setVelocity(org.bukkit.util.Vector p0) {}
    @Override public void setVisibleByDefault(boolean p0) {}
    @Override public void setVisualFire(boolean p0) {}
    @Override public void setWalkSpeed(float p0) {}
    @Override public boolean setWindowProperty(org.bukkit.inventory.InventoryView.Property p0, int p1) { return false; }
    @Override public void setWorldBorder(org.bukkit.WorldBorder p0) {}
    @Override public void showDemoScreen() {}
    @Override public void showEntity(org.bukkit.plugin.Plugin p0, org.bukkit.entity.Entity p1) {}
    @Override public void showPlayer(org.bukkit.entity.Player p0) {}
    @Override public void showPlayer(org.bukkit.plugin.Plugin p0, org.bukkit.entity.Player p1) {}
    @Override public boolean sleep(org.bukkit.Location p0, boolean p1) { return false; }
    @Override public void spawnParticle(org.bukkit.Particle p0, org.bukkit.Location p1, int p2) {}
    @Override public <T> void spawnParticle(org.bukkit.Particle p0, org.bukkit.Location p1, int p2, T p3) {}
    @Override public void spawnParticle(org.bukkit.Particle p0, org.bukkit.Location p1, int p2, double p3, double p4, double p5) {}
    @Override public <T> void spawnParticle(org.bukkit.Particle p0, org.bukkit.Location p1, int p2, double p3, double p4, double p5, T p6) {}
    @Override public void spawnParticle(org.bukkit.Particle p0, org.bukkit.Location p1, int p2, double p3, double p4, double p5, double p6) {}
    @Override public <T> void spawnParticle(org.bukkit.Particle p0, org.bukkit.Location p1, int p2, double p3, double p4, double p5, double p6, T p7) {}
    @Override public void spawnParticle(org.bukkit.Particle p0, double p1, double p2, double p3, int p4) {}
    @Override public <T> void spawnParticle(org.bukkit.Particle p0, double p1, double p2, double p3, int p4, T p5) {}
    @Override public void spawnParticle(org.bukkit.Particle p0, double p1, double p2, double p3, int p4, double p5, double p6, double p7) {}
    @Override public <T> void spawnParticle(org.bukkit.Particle p0, double p1, double p2, double p3, int p4, double p5, double p6, double p7, T p8) {}
    @Override public void spawnParticle(org.bukkit.Particle p0, double p1, double p2, double p3, int p4, double p5, double p6, double p7, double p8) {}
    @Override public <T> void spawnParticle(org.bukkit.Particle p0, double p1, double p2, double p3, int p4, double p5, double p6, double p7, double p8, T p9) {}
    @Override public void stopAllSounds() {}
    @Override public void stopSound(org.bukkit.Sound p0) {}
    @Override public void stopSound(org.bukkit.Sound p0, org.bukkit.SoundCategory p1) {}
    @Override public void stopSound(org.bukkit.SoundCategory p0) {}
    @Override public void stopSound(java.lang.String p0) {}
    @Override public void stopSound(java.lang.String p0, org.bukkit.SoundCategory p1) {}
    @Override public void swingMainHand() {}
    @Override public void swingOffHand() {}
    @Override public boolean teleport(org.bukkit.entity.Entity p0) { return false; }
    @Override public boolean teleport(org.bukkit.entity.Entity p0, org.bukkit.event.player.PlayerTeleportEvent.TeleportCause p1) { return false; }
    @Override public boolean teleport(org.bukkit.Location p0) { return false; }
    @Override public boolean teleport(org.bukkit.Location p0, org.bukkit.event.player.PlayerTeleportEvent.TeleportCause p1) { return false; }
    @Override public boolean undiscoverRecipe(org.bukkit.NamespacedKey p0) { return false; }
    @Override public int undiscoverRecipes(java.util.Collection<org.bukkit.NamespacedKey> p0) { return 0; }
    @Override public void updateCommands() {}
    @Override public void updateInventory() {}
    @Override public void wakeup(boolean p0) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftPlayer other)) return false;
        return handle.getUUID().equals(other.handle.getUUID());
    }

    @Override
    public int hashCode() { return handle.getUUID().hashCode(); }
}

