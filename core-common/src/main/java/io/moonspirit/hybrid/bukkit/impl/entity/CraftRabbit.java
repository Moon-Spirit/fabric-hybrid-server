package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Rabbit;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftRabbit extends CraftMob implements Rabbit {

    protected final net.minecraft.world.entity.animal.Rabbit rabbitHandle;

    public CraftRabbit(net.minecraft.world.entity.animal.Rabbit handle) {
        super(handle);
        this.rabbitHandle = handle;
    }

    public net.minecraft.world.entity.animal.Rabbit getRabbitHandle() {
        return rabbitHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.RABBIT;
    }

    @Override
    public Rabbit.Type getRabbitType() { return Rabbit.Type.BROWN; }
    @Override
    public void setRabbitType(Rabbit.Type type) {}

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
        if (!(obj instanceof CraftRabbit other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftRabbit{id=" + getEntityId() + "}";
    }
}
