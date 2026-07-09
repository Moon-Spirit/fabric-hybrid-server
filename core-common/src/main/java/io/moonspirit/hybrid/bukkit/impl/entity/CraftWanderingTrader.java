package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;

public class CraftWanderingTrader extends CraftMob implements WanderingTrader {

    protected final net.minecraft.world.entity.npc.WanderingTrader wanderingTraderHandle;

    public CraftWanderingTrader(net.minecraft.world.entity.npc.WanderingTrader handle) {
        super(handle);
        this.wanderingTraderHandle = handle;
    }

    public net.minecraft.world.entity.npc.WanderingTrader getWanderingTraderHandle() {
        return wanderingTraderHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.WANDERING_TRADER;
    }

    @Override
    public int getDespawnDelay() { return 0; }
    @Override
    public void setDespawnDelay(int delay) {}

    @Override
    public Inventory getInventory() { return null; }
    @Override
    public void setBreed(boolean breed) {}
    @Override
    public boolean getAgeLock() { return false; }
    @Override
    public void setAgeLock(boolean lock) {}
    @Override
    public boolean canBreed() { return false; }
    @Override
    public int getAge() { return 0; }
    @Override
    public void setAge(int age) {}
    @Override
    public void setBaby() {}
    @Override
    public void setAdult() {}
    @Override
    public boolean isAdult() { return true; }
    @Override
    public List<MerchantRecipe> getRecipes() { return List.of(); }
    @Override
    public void setRecipes(List<MerchantRecipe> recipes) {}
    @Override
    public MerchantRecipe getRecipe(int index) throws IndexOutOfBoundsException { return null; }
    @Override
    public void setRecipe(int index, MerchantRecipe recipe) throws IndexOutOfBoundsException {}
    @Override
    public int getRecipeCount() { return 0; }
    @Override
    public boolean isTrading() { return false; }
    @Override
    public org.bukkit.entity.HumanEntity getTrader() { return null; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftWanderingTrader other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftWanderingTrader{id=" + getEntityId() + "}";
    }
}
