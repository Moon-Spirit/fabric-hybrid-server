package io.moonspirit.hybrid.bukkit.impl.entity;

import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.phys.Vec3;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pose;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CraftEntity implements org.bukkit.entity.Entity {

    protected final net.minecraft.world.entity.Entity handle;

    public CraftEntity(net.minecraft.world.entity.Entity handle) { this.handle = handle; }
    public net.minecraft.world.entity.Entity getHandle() { return handle; }

    @Override public EntityType getType() {
        return EntityType.UNKNOWN;
    }
    @Override public Location getLocation() {
        var p = handle.position();
        return new Location(null, p.x, p.y, p.z, handle.getYRot(), handle.getXRot());
    }
    @Override public Location getLocation(Location loc) {
        if (loc == null) return null;
        var p = handle.position();
        loc.setX(p.x); loc.setY(p.y); loc.setZ(p.z);
        loc.setYaw(handle.getYRot()); loc.setPitch(handle.getXRot());
        return loc;
    }
    @Override public World getWorld() { return null; }
    @Override public int getEntityId() { return handle.getId(); }
    @Override public UUID getUniqueId() { return handle.getUUID(); }
    @Override public String getName() { return handle.getName().getString(); }
    @Override public BlockFace getFacing() { return BlockFace.SOUTH; }
    @Override public PistonMoveReaction getPistonMoveReaction() { return PistonMoveReaction.MOVE; }
    @Override public boolean isVisibleByDefault() { return true; }
    @Override public void setVisibleByDefault(boolean v) {}
    @Override public Sound getSwimSound() { return null; }
    @Override public Sound getSwimSplashSound() { return null; }
    @Override public Sound getSwimHighSpeedSplashSound() { return null; }
    @Override public List<org.bukkit.entity.Entity> getNearbyEntities(double x, double y, double z) { return List.of(); }
    @Override public org.bukkit.entity.Entity.Spigot spigot() { return null; }

    @Override public boolean teleport(Location l) { return teleport(l, PlayerTeleportEvent.TeleportCause.PLUGIN); }
    @Override public boolean teleport(Location l, PlayerTeleportEvent.TeleportCause c) {
        if (l == null) return false;
        handle.teleportTo(l.getX(), l.getY(), l.getZ());
        handle.setYRot(l.getYaw()); handle.setXRot(l.getPitch());
        return true;
    }
    @Override public boolean teleport(org.bukkit.entity.Entity d) { return d != null && teleport(d.getLocation()); }
    @Override public boolean teleport(org.bukkit.entity.Entity d, PlayerTeleportEvent.TeleportCause c) { return d != null && teleport(d.getLocation(), c); }
    @Override public void remove() { handle.remove(RemovalReason.KILLED); }
    @Override public boolean isDead() { return !handle.isAlive(); }
    @Override public boolean isValid() { return handle.isAlive(); }
    @Override public Server getServer() { return null; }
    @Override public boolean isPersistent() { return true; }
    @Override public void setPersistent(boolean p) {}
    @Override public org.bukkit.entity.Entity getPassenger() { return null; }
    @Override public boolean setPassenger(org.bukkit.entity.Entity p) { return false; }
    @Override public List<org.bukkit.entity.Entity> getPassengers() { return List.of(); }
    @Override public boolean addPassenger(org.bukkit.entity.Entity p) { return false; }
    @Override public boolean removePassenger(org.bukkit.entity.Entity p) { return false; }
    @Override public boolean eject() { return false; }
    @Override public float getFallDistance() { return handle.fallDistance; }
    @Override public void setFallDistance(float d) { handle.fallDistance = d; }
    @Override public void setLastDamageCause(EntityDamageEvent e) {}
    @Override public EntityDamageEvent getLastDamageCause() { return null; }
    @Override public int getTicksLived() { return handle.tickCount; }
    @Override public void setTicksLived(int v) { handle.tickCount = v; }
    @Override public boolean isInsideVehicle() { return handle.isPassenger(); }
    @Override public boolean leaveVehicle() { if (handle.isPassenger()) { handle.stopRiding(); return true; } return false; }
    @Override public org.bukkit.entity.Entity getVehicle() {
        var vehicle = handle.getVehicle();
        return vehicle != null ? new CraftEntity(vehicle) : null;
    }
    @Override public void setCustomName(String n) {}
    @Override public String getCustomName() { return null; }
    @Override public void setCustomNameVisible(boolean v) {}
    @Override public boolean isCustomNameVisible() { return false; }
    @Override public void setGlowing(boolean g) {}
    @Override public boolean isGlowing() { return false; }
    @Override public void setInvulnerable(boolean i) {}
    @Override public boolean isInvulnerable() { return false; }
    @Override public boolean isSilent() { return false; }
    @Override public void setSilent(boolean s) {}
    @Override public boolean hasGravity() { return !handle.isNoGravity(); }
    @Override public void setGravity(boolean g) { handle.setNoGravity(!g); }
    @Override public int getMaxFireTicks() { return 0; }
    @Override public void setFireTicks(int t) { handle.setRemainingFireTicks(t); }
    @Override public int getFireTicks() { return handle.getRemainingFireTicks(); }
    @Override public void setVisualFire(boolean v) {}
    @Override public boolean isVisualFire() { return false; }
    @Override public int getFreezeTicks() { return handle.getTicksFrozen(); }
    @Override public void setFreezeTicks(int t) { handle.setTicksFrozen(t); }
    @Override public int getMaxFreezeTicks() { return 0; }
    @Override public boolean isFrozen() { return handle.isFullyFrozen(); }
    @Override public boolean isInWater() { return handle.isInWater(); }
    public boolean isInLava() { return handle.isInLava(); }
    public boolean isUnderWater() { return handle.isUnderWater(); }
    public boolean isInWaterOrRain() { return handle.isInWaterRainOrBubble(); }
    public boolean isInWaterOrBubbleColumn() { return handle.isInWaterOrBubble(); }
    public boolean isInWaterOrRainOrBubble() { return handle.isInWaterRainOrBubble(); }
    public boolean isInBubbleColumn() { return false; }
    public boolean isInRain() { return false; }
    public boolean isInBubbleColumnOrRain() { return false; }
    public boolean isInOrOnBubbleColumn() { return false; }
    public boolean isFreezing() { return handle.getTicksFrozen() > 0; }
    public boolean isInWall() { return handle.isInWall(); }
    public boolean isInVoid() { return false; }
    @Override public Set<String> getScoreboardTags() { return handle.getTags(); }
    @Override public boolean addScoreboardTag(String t) { return handle.addTag(t); }
    @Override public boolean removeScoreboardTag(String t) { return handle.removeTag(t); }
    @Override public void setMetadata(String k, MetadataValue v) {}
    @Override public List<MetadataValue> getMetadata(String k) { return null; }
    @Override public boolean hasMetadata(String k) { return false; }
    @Override public void removeMetadata(String k, Plugin p) {}
    @Override public void sendMessage(String m) {}
    @Override public void sendMessage(String... m) {}
    @Override public void sendMessage(UUID sender, String message) {}
    @Override public void sendMessage(UUID sender, String... messages) {}
    @Override public boolean isPermissionSet(String n) { return false; }
    @Override public boolean isPermissionSet(Permission p) { return false; }
    @Override public boolean hasPermission(String n) { return false; }
    @Override public boolean hasPermission(Permission p) { return false; }
    @Override public PermissionAttachment addAttachment(Plugin p, String n, boolean v) { return null; }
    @Override public PermissionAttachment addAttachment(Plugin p) { return null; }
    @Override public PermissionAttachment addAttachment(Plugin p, String n, boolean v, int t) { return null; }
    @Override public PermissionAttachment addAttachment(Plugin p, int t) { return null; }
    @Override public void removeAttachment(PermissionAttachment a) {}
    @Override public void recalculatePermissions() {}
    @Override public Set<PermissionAttachmentInfo> getEffectivePermissions() { return null; }
    @Override public boolean isOp() { return false; }
    @Override public void setOp(boolean v) {}
    @Override public double getHeight() { return handle.getBbHeight(); }
    @Override public double getWidth() { return handle.getBbWidth(); }
    @Override public BoundingBox getBoundingBox() {
        var a = handle.getBoundingBox();
        return a != null ? new BoundingBox(a.minX, a.minY, a.minZ, a.maxX, a.maxY, a.maxZ) : null;
    }
    @Override public boolean isOnGround() { return handle.onGround(); }
    @Override public boolean isEmpty() { return false; }
    public boolean isInvisible() { return handle.isInvisible(); }
    public void setInvisible(boolean i) { handle.setInvisible(i); }
    public EntityCategory getCategory() { return EntityCategory.NONE; }
    public SpawnCategory getSpawnCategory() { return SpawnCategory.MISC; }
    public Pose getPose() { return Pose.STANDING; }
    public boolean isCollidable() { return true; }
    public void setCollidable(boolean c) {}
    @Override public void playEffect(EntityEffect t) {}
    @Override public Vector getVelocity() {
        var m = handle.getDeltaMovement();
        return new Vector(m.x, m.y, m.z);
    }
    @Override public void setVelocity(Vector v) { handle.setDeltaMovement(v.getX(), v.getY(), v.getZ()); }
    @Override public void setRotation(float y, float p) { handle.setYRot(y); handle.setXRot(p); }
    public boolean isBurning() { return handle.isOnFire(); }
    public boolean isTicking() { return true; }
    public boolean isInWorld() { return handle.level() != null; }
    public int getPortalCooldown() { return 0; }
    public void setPortalCooldown(int c) {}
    public org.bukkit.scoreboard.Scoreboard getScoreboard() { return null; }
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() { return null; }
    public Map<String, Object> serialize() { return null; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CraftEntity other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }
    @Override public int hashCode() { return getUniqueId().hashCode(); }
    @Override public String toString() { return "CraftEntity{id=" + getEntityId() + ",type=" + getType() + "}"; }
}
