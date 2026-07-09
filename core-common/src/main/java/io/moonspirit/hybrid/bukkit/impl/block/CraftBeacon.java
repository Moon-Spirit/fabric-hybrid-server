package io.moonspirit.hybrid.bukkit.impl.block;

import io.moonspirit.hybrid.bukkit.impl.CraftWorld;
import net.minecraft.core.BlockPos;
import org.bukkit.entity.LivingEntity;
import org.bukkit.block.Beacon;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.Collections;

public class CraftBeacon extends CraftBlockState implements Beacon {

    public CraftBeacon(CraftWorld world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public org.bukkit.persistence.PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public String getLock() {
        return null;
    }

    @Override
    public void setLock(String key) {
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
    }

    @Override
    public Collection<LivingEntity> getEntitiesInRange() { return Collections.emptyList(); }

    @Override
    public int getTier() { return 0; }

    @Override
    public PotionEffect getPrimaryEffect() { return null; }

    @Override
    public void setPrimaryEffect(PotionEffectType effect) {}

    @Override
    public PotionEffect getSecondaryEffect() { return null; }

    @Override
    public void setSecondaryEffect(PotionEffectType effect) {}
}
