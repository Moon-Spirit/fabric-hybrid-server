package io.moonspirit.hybrid.bukkit.impl.map;

import org.bukkit.map.MapCursor;

public class CraftMapCursor {

    private byte x;
    private byte y;
    private byte direction;
    private byte rawType;
    private boolean visible;
    private String caption;

    public CraftMapCursor() {
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        this.rawType = 0;
        this.visible = true;
        this.caption = "";
    }

    public CraftMapCursor(byte x, byte y, byte direction, byte rawType, boolean visible) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.rawType = rawType;
        this.visible = visible;
        this.caption = "";
    }

    public CraftMapCursor(byte x, byte y, byte direction, MapCursor.Type type, boolean visible) {
        this(x, y, direction, type.getValue(), visible);
    }

    public CraftMapCursor(byte x, byte y, byte direction, byte rawType, boolean visible, String caption) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.rawType = rawType;
        this.visible = visible;
        this.caption = caption;
    }

    public CraftMapCursor(byte x, byte y, byte direction, MapCursor.Type type, boolean visible, String caption) {
        this(x, y, direction, type.getValue(), visible, caption);
    }

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public byte getDirection() {
        return direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    public byte getRawType() {
        return rawType;
    }

    public void setRawType(byte rawType) {
        this.rawType = rawType;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public MapCursor toBukkit() {
        return new MapCursor(x, y, direction, rawType, visible, caption);
    }
}
