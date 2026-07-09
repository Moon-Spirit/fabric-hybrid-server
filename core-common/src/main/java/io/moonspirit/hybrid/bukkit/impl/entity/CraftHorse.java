package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftHorse extends CraftLivingEntity implements Horse {

    protected final net.minecraft.world.entity.animal.horse.Horse horseHandle;

    public CraftHorse(net.minecraft.world.entity.animal.horse.Horse handle) {
        super(handle);
        this.horseHandle = handle;
    }

    public net.minecraft.world.entity.animal.horse.Horse getHorseHandle() {
        return horseHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.HORSE;
    }

    @Override
    public Color getColor() { return Color.BROWN; }

    @Override
    public void setColor(Color color) {}

    @Override
    public Style getStyle() { return Style.NONE; }

    @Override
    public void setStyle(Style style) {}

    @Override
    public boolean isCarryingChest() { return false; }

    @Override
    public void setCarryingChest(boolean chest) {}

    @Override
    public HorseInventory getInventory() { return null; }

    @Override
    public Variant getVariant() { return Variant.HORSE; }

    @Override
    public void setVariant(Variant variant) {}

    @Override
    public int getDomestication() { return 0; }

    @Override
    public void setDomestication(int domestication) {}

    @Override
    public int getMaxDomestication() { return 100; }

    @Override
    public void setMaxDomestication(int maxDomestication) {}

    @Override
    public double getJumpStrength() { return 0.7; }

    @Override
    public void setJumpStrength(double strength) {}

    @Override
    public boolean isEatingHaystack() { return false; }

    @Override
    public void setEatingHaystack(boolean eatingHaystack) {}

    @Override
    public boolean isTamed() { return horseHandle.isTamed(); }

    @Override
    public void setTamed(boolean tamed) {}

    @Override
    public org.bukkit.entity.AnimalTamer getOwner() { return null; }

    @Override
    public void setOwner(org.bukkit.entity.AnimalTamer owner) {}

    @Override
    public Sound getAmbientSound() { return null; }

    @Override
    public void setTarget(org.bukkit.entity.LivingEntity target) {}

    @Override
    public org.bukkit.entity.LivingEntity getTarget() { return null; }

    @Override
    public void setAware(boolean aware) {}

    @Override
    public boolean isAware() { return true; }

    @Override
    public void setLootTable(org.bukkit.loot.LootTable table) {}

    @Override
    public org.bukkit.loot.LootTable getLootTable() { return null; }

    @Override
    public void setSeed(long seed) {}

    @Override
    public long getSeed() { return 0; }

    // ===== Animals =====

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

    // ===== Ageable =====

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftHorse other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftHorse{id=" + getEntityId() + "}";
    }
}
