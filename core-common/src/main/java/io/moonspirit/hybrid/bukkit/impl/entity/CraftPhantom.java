package io.moonspirit.hybrid.bukkit.impl.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;

public class CraftPhantom extends CraftMob implements Phantom {

    protected final net.minecraft.world.entity.monster.Phantom phantomHandle;

    public CraftPhantom(net.minecraft.world.entity.monster.Phantom handle) {
        super(handle);
        this.phantomHandle = handle;
    }

    public net.minecraft.world.entity.monster.Phantom getPhantomHandle() {
        return phantomHandle;
    }

    @Override
    public EntityType getType() {
        return EntityType.PHANTOM;
    }

    @Override
    public int getSize() { return 0; }

    @Override
    public void setSize(int size) {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CraftPhantom other)) return false;
        return getUniqueId().equals(other.getUniqueId());
    }

    @Override
    public int hashCode() {
        return getUniqueId().hashCode();
    }

    @Override
    public String toString() {
        return "CraftPhantom{id=" + getEntityId() + "}";
    }
}
