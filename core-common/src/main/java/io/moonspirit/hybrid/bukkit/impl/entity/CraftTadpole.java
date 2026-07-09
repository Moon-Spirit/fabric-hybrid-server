package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Tadpole;

public class CraftTadpole extends CraftMob implements Tadpole {

    protected final net.minecraft.world.entity.animal.frog.Tadpole tadpoleHandle;

    public CraftTadpole(net.minecraft.world.entity.animal.frog.Tadpole handle) {
        super(handle);
        this.tadpoleHandle = handle;
    }

    public net.minecraft.world.entity.animal.frog.Tadpole getTadpoleHandle() {
        return tadpoleHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.TADPOLE;
    }

    @Override
    public int getAge() { return 0; }
    @Override
    public void setAge(int age) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftTadpole other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftTadpole{id=" + getEntityId() + "}";
    }
}
