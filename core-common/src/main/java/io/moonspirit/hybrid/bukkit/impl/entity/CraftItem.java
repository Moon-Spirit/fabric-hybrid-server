package io.moonspirit.hybrid.bukkit.impl.entity;

import io.moonspirit.hybrid.bukkit.impl.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CraftItem extends CraftEntity implements Item {

    protected final net.minecraft.world.entity.item.ItemEntity itemHandle;

    public CraftItem(net.minecraft.world.entity.item.ItemEntity handle) {
        super(handle);
        this.itemHandle = handle;
    }

    public net.minecraft.world.entity.item.ItemEntity getItemHandle() {
        return itemHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.DROPPED_ITEM;
    }

    @Override
    public ItemStack getItemStack() {
        return new CraftItemStack(itemHandle.getItem());
    }

    @Override
    public void setItemStack(ItemStack stack) {
        if (stack instanceof CraftItemStack craftStack) {
            itemHandle.setItem(craftStack.getHandle());
        } else {
            itemHandle.setItem(CraftItemStack.fromBukkitCopy(stack).getHandle());
        }
    }

    @Override
    public int getPickupDelay() { return itemHandle.hasPickUpDelay() ? Integer.MAX_VALUE : 0; }

    @Override
    public void setPickupDelay(int delay) { itemHandle.setPickUpDelay(delay); }

    @Override
    public void setUnlimitedLifetime(boolean unlimited) {
        if (unlimited) itemHandle.setUnlimitedLifetime();
    }

    @Override
    public boolean isUnlimitedLifetime() { return false; }

    @Override
    public void setOwner(UUID owner) { itemHandle.setTarget(owner); }

    @Override
    public UUID getOwner() { return null; }

    @Override
    public void setThrower(UUID thrower) { itemHandle.setThrower(thrower); }

    @Override
    public UUID getThrower() { return null; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftItem other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftItem{id=" + getEntityId() + "}";
    }
}
