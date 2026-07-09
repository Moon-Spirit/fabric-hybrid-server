package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftAxolotl extends CraftMob implements Axolotl {

    protected final net.minecraft.world.entity.animal.axolotl.Axolotl axolotlHandle;

    public CraftAxolotl(net.minecraft.world.entity.animal.axolotl.Axolotl handle) {
        super(handle);
        this.axolotlHandle = handle;
    }

    public net.minecraft.world.entity.animal.axolotl.Axolotl getAxolotlHandle() {
        return axolotlHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.AXOLOTL;
    }

    @Override
    public boolean isPlayingDead() { return false; }
    @Override
    public void setPlayingDead(boolean playingDead) {}
    @Override
    public Axolotl.Variant getVariant() { return Axolotl.Variant.LUCY; }
    @Override
    public void setVariant(Axolotl.Variant variant) {}

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftAxolotl other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftAxolotl{id=" + getEntityId() + "}";
    }
}
