package io.moonspirit.hybrid.bukkit.impl.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class CraftMapRenderer extends MapRenderer {

    public CraftMapRenderer() {
        super();
    }

    public CraftMapRenderer(boolean contextual) {
        super(contextual);
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
    }
}
