package io.moonspirit.hybrid.bukkit.impl.potion;

import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionEffectType;

public class CraftPotionEffectType extends PotionEffectType {

    private final NamespacedKey key;
    private final String name;
    private final boolean instant;
    private final Color color;

    public CraftPotionEffectType(int id, NamespacedKey key, String name, boolean instant, Color color) {
        super(id, key);
        this.key = key;
        this.name = name;
        this.instant = instant;
        this.color = color;
    }

    @Override
    public double getDurationModifier() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInstant() {
        return instant;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
