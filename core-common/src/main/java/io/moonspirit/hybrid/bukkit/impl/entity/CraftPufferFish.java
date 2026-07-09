package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.PufferFish;

public class CraftPufferFish extends CraftMob implements PufferFish {

    protected final net.minecraft.world.entity.animal.Pufferfish pufferFishHandle;

    public CraftPufferFish(net.minecraft.world.entity.animal.Pufferfish handle) {
        super(handle);
        this.pufferFishHandle = handle;
    }

    public net.minecraft.world.entity.animal.Pufferfish getPufferFishHandle() {
        return pufferFishHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.PUFFERFISH;
    }

    @Override
    public int getPuffState() { return 0; }
    @Override
    public void setPuffState(int state) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftPufferFish other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftPufferFish{id=" + getEntityId() + "}";
    }
}
