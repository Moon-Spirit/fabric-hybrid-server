package io.moonspirit.hybrid.bukkit.impl.map;

import org.bukkit.World;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CraftMapView implements MapView {

    private final int id;
    private Scale scale = Scale.NORMAL;
    private int centerX;
    private int centerZ;
    private World world;
    private boolean trackingPosition = true;
    private boolean unlimitedTracking;
    private boolean locked;
    private final List<MapRenderer> renderers = new ArrayList<>();

    public CraftMapView(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isVirtual() {
        return false;
    }

    @Override
    public Scale getScale() {
        return scale;
    }

    @Override
    public void setScale(Scale scale) {
        this.scale = scale;
    }

    @Override
    public int getCenterX() {
        return centerX;
    }

    @Override
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    @Override
    public int getCenterZ() {
        return centerZ;
    }

    @Override
    public void setCenterZ(int centerZ) {
        this.centerZ = centerZ;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public List<MapRenderer> getRenderers() {
        return Collections.unmodifiableList(renderers);
    }

    @Override
    public void addRenderer(MapRenderer renderer) {
        renderers.add(renderer);
    }

    @Override
    public boolean removeRenderer(MapRenderer renderer) {
        return renderers.remove(renderer);
    }

    @Override
    public boolean isTrackingPosition() {
        return trackingPosition;
    }

    @Override
    public void setTrackingPosition(boolean trackingPosition) {
        this.trackingPosition = trackingPosition;
    }

    @Override
    public boolean isUnlimitedTracking() {
        return unlimitedTracking;
    }

    @Override
    public void setUnlimitedTracking(boolean unlimitedTracking) {
        this.unlimitedTracking = unlimitedTracking;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
