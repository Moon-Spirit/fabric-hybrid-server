package io.moonspirit.hybrid.bukkit.impl.entity;

import io.moonspirit.hybrid.bukkit.impl.CraftPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CraftLivingEntity extends CraftEntity implements org.bukkit.entity.LivingEntity {

    protected final LivingEntity livingHandle;

    public CraftLivingEntity(LivingEntity handle) {
        super(handle);
        this.livingHandle = handle;
    }

    public LivingEntity getLivingHandle() {
        return livingHandle;
    }

    @Override public double getHealth() { return livingHandle.getHealth(); }
    @Override public void setHealth(double health) { livingHandle.setHealth((float) health); }
    @Override public double getMaxHealth() {
        var attr = livingHandle.getAttribute(Attributes.MAX_HEALTH);
        return attr != null ? attr.getValue() : 20.0;
    }
    @Override public void setMaxHealth(double health) {
        var attr = livingHandle.getAttribute(Attributes.MAX_HEALTH);
        if (attr != null) attr.setBaseValue(health);
    }
    @Override public void resetMaxHealth() {}
    @Override public AttributeInstance getAttribute(Attribute attribute) { return null; }
    public void registerAttribute(Attribute attribute) {}
    @Override public double getAbsorptionAmount() { return livingHandle.getAbsorptionAmount(); }
    @Override public void setAbsorptionAmount(double amount) { livingHandle.setAbsorptionAmount((float) amount); }

    @Override public EntityEquipment getEquipment() { return null; }

    @Override public Collection<PotionEffect> getActivePotionEffects() {
        List<PotionEffect> result = new ArrayList<>();
        for (MobEffectInstance inst : livingHandle.getActiveEffects()) {
            PotionEffectType type = PotionEffectType.getByName(inst.getEffect().getDescriptionId().replace("effect.", "").replace(".", ""));
            if (type != null) {
                result.add(new PotionEffect(type, inst.getDuration(), inst.getAmplifier(), inst.isAmbient(), inst.isVisible()));
            }
        }
        return result;
    }
    @Override public boolean addPotionEffect(PotionEffect effect) { return addPotionEffect(effect, false); }
    @Override public boolean addPotionEffect(PotionEffect effect, boolean force) {
        if (effect == null) return false;
        net.minecraft.world.effect.MobEffect nmsEffect = net.minecraft.world.effect.MobEffect.byId(effect.getType().getId());
        if (nmsEffect == null) return false;
        livingHandle.addEffect(new MobEffectInstance(nmsEffect, effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles(), effect.hasIcon()));
        return true;
    }
    @Override public boolean addPotionEffects(Collection<PotionEffect> effects) {
        if (effects == null) return false;
        for (PotionEffect effect : effects) addPotionEffect(effect);
        return true;
    }
    @Override public void removePotionEffect(PotionEffectType type) {
        if (type == null) return;
        net.minecraft.world.effect.MobEffect nmsEffect = net.minecraft.world.effect.MobEffect.byId(type.getId());
        if (nmsEffect != null) livingHandle.removeEffect(nmsEffect);
    }
    @Override public boolean hasPotionEffect(PotionEffectType type) {
        if (type == null) return false;
        net.minecraft.world.effect.MobEffect nmsEffect = net.minecraft.world.effect.MobEffect.byId(type.getId());
        return nmsEffect != null && livingHandle.hasEffect(nmsEffect);
    }
    @Override public PotionEffect getPotionEffect(PotionEffectType type) {
        if (type == null) return null;
        net.minecraft.world.effect.MobEffect nmsEffect = net.minecraft.world.effect.MobEffect.byId(type.getId());
        if (nmsEffect == null) return null;
        MobEffectInstance inst = livingHandle.getEffect(nmsEffect);
        if (inst == null) return null;
        return new PotionEffect(type, inst.getDuration(), inst.getAmplifier(), inst.isAmbient(), inst.isVisible());
    }

    @Override public void damage(double amount) { livingHandle.hurt(livingHandle.damageSources().generic(), (float) amount); }
    @Override public void damage(double amount, org.bukkit.entity.Entity source) {
        if (source instanceof CraftLivingEntity ce) {
            livingHandle.hurt(livingHandle.damageSources().mobAttack(ce.livingHandle), (float) amount);
        } else if (source instanceof CraftEntity ce && ce.getHandle() instanceof LivingEntity le) {
            livingHandle.hurt(livingHandle.damageSources().mobAttack(le), (float) amount);
        } else {
            livingHandle.hurt(livingHandle.damageSources().generic(), (float) amount);
        }
    }
    @Override public double getLastDamage() { return 0; }
    @Override public void setLastDamage(double damage) {}
    @Override public void playHurtAnimation(float yaw) {}
    @Override public int getArrowsInBody() { return 0; }
    @Override public void setArrowsInBody(int count) {}
    @Override public int getArrowCooldown() { return 0; }
    @Override public void setArrowCooldown(int ticks) {}
    @Override public Player getKiller() {
        net.minecraft.world.entity.LivingEntity killer = livingHandle.getKillCredit();
        if (killer instanceof net.minecraft.world.entity.player.Player p && p instanceof net.minecraft.server.level.ServerPlayer sp) {
            return new CraftPlayer(sp);
        }
        return null;
    }
    @Override public Location getEyeLocation() {
        Location loc = getLocation();
        if (loc != null) loc.setY(loc.getY() + getEyeHeight());
        return loc;
    }
    @Override public double getEyeHeight() { return livingHandle.getEyeHeight(); }
    @Override public double getEyeHeight(boolean ignorePose) { return getEyeHeight(); }
    @Override public boolean canBreatheUnderwater() { return false; }
    @Override public boolean isSleeping() { return false; }
    @Override public boolean isInvisible() { return handle.isInvisible(); }
    @Override public void setInvisible(boolean invisible) { handle.setInvisible(invisible); }
    @Override public boolean isBurning() { return handle.isOnFire(); }
    @Override public boolean isGliding() { return false; }
    @Override public void setGliding(boolean gliding) {}
    @Override public SpawnCategory getSpawnCategory() { return SpawnCategory.MISC; }
    @Override public Pose getPose() { return Pose.STANDING; }
    @Override public org.bukkit.entity.EntityCategory getCategory() { return org.bukkit.entity.EntityCategory.NONE; }

    @Override public boolean getCanPickupItems() { return false; }
    @Override public void setCanPickupItems(boolean pickup) {}
    @Override public boolean isLeashed() { return false; }
    @Override public boolean setLeashHolder(org.bukkit.entity.Entity holder) { return false; }
    @Override public org.bukkit.entity.Entity getLeashHolder() { return null; }
    @Override public void setAI(boolean ai) {}
    @Override public boolean hasAI() { return true; }
    @Override public void attack(org.bukkit.entity.Entity target) {}
    @Override public void swingMainHand() {}
    @Override public void swingOffHand() {}
    @Override public Set<UUID> getCollidableExemptions() { return Set.of(); }
    @Override public <T> T getMemory(org.bukkit.entity.memory.MemoryKey<T> key) { return null; }
    @Override public <T> void setMemory(org.bukkit.entity.memory.MemoryKey<T> key, T value) {}
    @Override public Sound getHurtSound() { return null; }
    @Override public Sound getDeathSound() { return null; }
    @Override public Sound getFallDamageSound(int height) { return null; }
    @Override public Sound getFallDamageSoundBig() { return null; }
    @Override public Sound getFallDamageSoundSmall() { return null; }
    @Override public Sound getDrinkingSound(ItemStack item) { return null; }
    @Override public Sound getEatingSound(ItemStack item) { return null; }
    @Override public Sound getSwimSound() { return null; }
    @Override public Sound getSwimSplashSound() { return null; }
    @Override public Sound getSwimHighSpeedSplashSound() { return null; }
    @Override public boolean getRemoveWhenFarAway() { return false; }
    @Override public void setRemoveWhenFarAway(boolean remove) {}
    @Override public int getRemainingAir() { return 300; }
    @Override public void setRemainingAir(int ticks) {}
    @Override public int getMaximumAir() { return 300; }
    @Override public void setMaximumAir(int ticks) {}
    @Override public int getMaximumNoDamageTicks() { return 20; }
    @Override public void setMaximumNoDamageTicks(int ticks) {}
    @Override public int getNoDamageTicks() { return 0; }
    @Override public void setNoDamageTicks(int ticks) {}
    @Override public int getNoActionTicks() { return 0; }
    @Override public void setNoActionTicks(int ticks) {}
    @Override public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) { return null; }
    @Override public Block getTargetBlock(Set<Material> transparent, int maxDistance) { return null; }
    @Override public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) { return null; }
    @Override public Block getTargetBlockExact(int maxDistance) { return null; }
    @Override public Block getTargetBlockExact(int maxDistance, org.bukkit.FluidCollisionMode fluidCollisionMode) { return null; }
    @Override public RayTraceResult rayTraceBlocks(double maxDistance) { return null; }
    @Override public RayTraceResult rayTraceBlocks(double maxDistance, org.bukkit.FluidCollisionMode fluidCollisionMode) { return null; }
    @Override public boolean hasLineOfSight(org.bukkit.entity.Entity other) { return false; }
    @Override public boolean isClimbing() { return false; }
    @Override public boolean isRiptiding() { return false; }
    @Override public boolean isSwimming() { return false; }
    @Override public void setSwimming(boolean swimming) {}
    @Override public boolean isCollidable() { return true; }
    @Override public void setCollidable(boolean collidable) {}
    @Override public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> type) { return null; }
    @Override public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> type, org.bukkit.util.Vector velocity) { return null; }

    @Override public Map<String, Object> serialize() { return null; }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftLivingEntity other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }
    @Override public int hashCode() { return getUniqueId().hashCode(); }
    @Override public String toString() { return "CraftLivingEntity{id=" + getEntityId() + "}"; }
}
