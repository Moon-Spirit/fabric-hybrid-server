package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftPanda extends CraftMob implements Panda {

    protected final net.minecraft.world.entity.animal.Panda pandaHandle;

    public CraftPanda(net.minecraft.world.entity.animal.Panda handle) {
        super(handle);
        this.pandaHandle = handle;
    }

    public net.minecraft.world.entity.animal.Panda getPandaHandle() {
        return pandaHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.PANDA;
    }

    @Override
    public Panda.Gene getMainGene() { return Panda.Gene.NORMAL; }
    @Override
    public void setMainGene(Panda.Gene gene) {}
    @Override
    public Panda.Gene getHiddenGene() { return Panda.Gene.NORMAL; }
    @Override
    public void setHiddenGene(Panda.Gene gene) {}
    @Override
    public boolean isRolling() { return false; }
    @Override
    public void setRolling(boolean rolling) {}
    @Override
    public boolean isSneezing() { return false; }
    @Override
    public void setSneezing(boolean sneezing) {}
    @Override
    public boolean isOnBack() { return false; }
    @Override
    public void setOnBack(boolean onBack) {}
    @Override
    public boolean isEating() { return false; }
    @Override
    public void setEating(boolean eating) {}
    @Override
    public boolean isScared() { return false; }
    @Override
    public int getUnhappyTicks() { return 0; }
    @Override
    public boolean isSitting() { return false; }
    @Override
    public void setSitting(boolean sitting) {}

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
        if (!(obj instanceof CraftPanda other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftPanda{id=" + getEntityId() + "}";
    }
}
