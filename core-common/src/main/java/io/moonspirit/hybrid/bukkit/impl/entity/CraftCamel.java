package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.Camel;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftCamel extends CraftMob implements Camel {

    protected final net.minecraft.world.entity.animal.camel.Camel camelHandle;

    public CraftCamel(net.minecraft.world.entity.animal.camel.Camel handle) {
        super(handle);
        this.camelHandle = handle;
    }

    public net.minecraft.world.entity.animal.camel.Camel getCamelHandle() {
        return camelHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.CAMEL;
    }

    @Override
    public boolean isDashing() { return false; }
    @Override
    public void setDashing(boolean dashing) {}
    @Override
    public boolean isSitting() { return false; }
    @Override
    public void setSitting(boolean sitting) {}

    @Override
    public Horse.Variant getVariant() { return Horse.Variant.HORSE; }
    @Override
    public void setVariant(Horse.Variant variant) {}
    @Override
    public int getDomestication() { return 0; }
    @Override
    public void setDomestication(int domestication) {}
    @Override
    public int getMaxDomestication() { return 100; }
    @Override
    public void setMaxDomestication(int maxDomestication) {}
    @Override
    public double getJumpStrength() { return 0; }
    @Override
    public void setJumpStrength(double strength) {}
    @Override
    public boolean isEatingHaystack() { return false; }
    @Override
    public void setEatingHaystack(boolean eatingHaystack) {}
    @Override
    public AbstractHorseInventory getInventory() { return null; }

    @Override
    public UUID getBreedCause() { return null; }
    @Override
    public void setBreedCause(UUID cause) {}
    @Override
    public boolean isLoveMode() { return false; }
    @Override
    public int getLoveModeTicks() { return 0; }
    @Override
    public void setLoveModeTicks(int ticks) {}
    @Override
    public boolean isBreedItem(ItemStack stack) { return false; }
    @Override
    public boolean isBreedItem(Material material) { return false; }
    @Override
    public int getAge() { return 0; }
    @Override
    public void setAge(int age) {}
    @Override
    public void setAgeLock(boolean lock) {}
    @Override
    public boolean getAgeLock() { return false; }
    @Override
    public void setBaby() {}
    @Override
    public void setAdult() {}
    @Override
    public boolean isAdult() { return true; }
    @Override
    public boolean canBreed() { return false; }
    @Override
    public void setBreed(boolean breed) {}
    @Override
    public org.bukkit.entity.AnimalTamer getOwner() { return null; }
    @Override
    public void setOwner(org.bukkit.entity.AnimalTamer owner) {}
    @Override
    public boolean isTamed() { return false; }
    @Override
    public void setTamed(boolean tamed) {}
    @Override
    public org.bukkit.util.Vector getVelocity() { return getVelocity(); }
    @Override
    public void setVelocity(org.bukkit.util.Vector velocity) { setVelocity(velocity); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftCamel other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftCamel{id=" + getEntityId() + "}";
    }
}
