package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CraftVillager extends CraftLivingEntity implements Villager {

    protected final net.minecraft.world.entity.npc.Villager villagerHandle;

    public CraftVillager(net.minecraft.world.entity.npc.Villager handle) {
        super(handle);
        this.villagerHandle = handle;
    }

    public net.minecraft.world.entity.npc.Villager getVillagerHandle() {
        return villagerHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.VILLAGER;
    }

    @Override
    public Villager.Profession getProfession() { return null; }

    @Override
    public void setProfession(Villager.Profession profession) {}

    @Override
    public Villager.Type getVillagerType() { return Villager.Type.PLAINS; }

    @Override
    public void setVillagerType(Villager.Type type) {}

    @Override
    public int getVillagerLevel() { return 1; }

    @Override
    public void setVillagerLevel(int level) {}

    @Override
    public int getVillagerExperience() { return 0; }

    @Override
    public void setVillagerExperience(int experience) {}

    @Override
    public boolean sleep(org.bukkit.Location location) { return false; }

    @Override
    public void wakeup() {}

    @Override
    public void shakeHead() {}

    @Override
    public org.bukkit.entity.ZombieVillager zombify() { return null; }

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
    public Inventory getInventory() { return null; }

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

    @Override
    public boolean isRiptiding() { return false; }
    @Override
    public boolean isSleeping() { return false; }
    @Override
    public boolean isClimbing() { return false; }
    @Override
    public boolean isSwimming() { return false; }
    @Override
    public void setSwimming(boolean swimming) {}
    @Override
    public org.bukkit.Sound getAmbientSound() { return null; }
    @Override
    public void setGliding(boolean gliding) {}
    @Override
    public boolean isGliding() { return false; }
    @Override
    public void setArrowsInBody(int count) {}
    @Override
    public int getArrowsInBody() { return 0; }
    @Override
    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> type) { return null; }
    @Override
    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> type, org.bukkit.util.Vector velocity) { return null; }
    @Override
    public boolean isCollidable() { return true; }
    @Override
    public void setCollidable(boolean collidable) {}
    @Override
    public Set<UUID> getCollidableExemptions() { return Set.of(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftVillager other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftVillager{id=" + getEntityId() + "}";
    }
}
