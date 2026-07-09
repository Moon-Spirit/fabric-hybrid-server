package io.moonspirit.hybrid.bukkit.impl.gamerule;

import org.bukkit.GameRule;

public class CraftGameRule {

    @SuppressWarnings("unchecked")
    public static <T> T getDefault(GameRule<T> gameRule) {
        if (gameRule.getType() == Boolean.class) {
            return (T) Boolean.TRUE;
        }
        if (gameRule.getType() == Integer.class) {
            return (T) Integer.valueOf(0);
        }
        return null;
    }

    public static <T> boolean validate(GameRule<T> gameRule, T value) {
        if (value == null) return false;
        return gameRule.getType().isInstance(value);
    }
}
