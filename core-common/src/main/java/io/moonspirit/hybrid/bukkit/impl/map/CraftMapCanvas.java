package io.moonspirit.hybrid.bukkit.impl.map;

import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapFont;
import org.bukkit.map.MapView;

import java.awt.Color;
import java.awt.Image;

public class CraftMapCanvas implements MapCanvas {

    private final MapView mapView;
    private final MapCursorCollection cursors = new MapCursorCollection();

    public CraftMapCanvas(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public MapView getMapView() {
        return mapView;
    }

    @Override
    public MapCursorCollection getCursors() {
        return cursors;
    }

    @Override
    public void setCursors(MapCursorCollection cursors) {
    }

    @Override
    public void setPixelColor(int x, int z, Color color) {
    }

    @Override
    public Color getPixelColor(int x, int z) {
        return Color.BLACK;
    }

    @Override
    public Color getBasePixelColor(int x, int z) {
        return Color.BLACK;
    }

    @Override
    public void setPixel(int x, int z, byte color) {
    }

    @Override
    public byte getPixel(int x, int z) {
        return 0;
    }

    @Override
    public byte getBasePixel(int x, int z) {
        return 0;
    }

    @Override
    public void drawImage(int x, int z, Image image) {
    }

    @Override
    public void drawText(int x, int z, MapFont font, String text) {
    }
}
