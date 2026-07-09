package io.moonspirit.hybrid.bukkit.impl.packs;

import org.bukkit.FeatureFlag;
import org.bukkit.NamespacedKey;
import org.bukkit.packs.DataPack;

import java.util.Collections;
import java.util.Set;

public class CraftDataPack implements DataPack {

    @Override
    public NamespacedKey getKey() {
        return null;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getPackFormat() {
        return 0;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public Compatibility getCompatibility() {
        return Compatibility.COMPATIBLE;
    }

    @Override
    public Set<FeatureFlag> getRequestedFeatures() {
        return Collections.emptySet();
    }

    @Override
    public Source getSource() {
        return Source.DEFAULT;
    }
}
