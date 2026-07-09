package io.moonspirit.hybrid.bukkit.impl.util;

import org.bukkit.util.BoundingBox;

public class CraftBoundingBox extends BoundingBox {

    public CraftBoundingBox() {
        super();
    }

    public CraftBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1, y1, z1, x2, y2, z2);
    }
}
