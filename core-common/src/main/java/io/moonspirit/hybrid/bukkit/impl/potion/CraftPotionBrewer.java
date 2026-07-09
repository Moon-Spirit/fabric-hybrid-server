package io.moonspirit.hybrid.bukkit.impl.potion;

import org.bukkit.potion.PotionBrewer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Collection;
import java.util.Collections;

public class CraftPotionBrewer implements PotionBrewer {

    @Override
    public PotionEffect createEffect(PotionEffectType potion, int duration, int amplifier) {
        return new PotionEffect(potion, duration, amplifier);
    }

    @Override
    public Collection<PotionEffect> getEffectsFromDamage(int damage) {
        return Collections.emptyList();
    }

    @Override
    public Collection<PotionEffect> getEffects(PotionType type, boolean upgraded, boolean extended) {
        return Collections.emptyList();
    }
}
