package io.moonspirit.hybrid.mod.util;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.Arrays;

public class EnumHelper {
    private static final Unsafe UNSAFE;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T makeEnum(Class<T> enumClass, String name, int ordinal, Class<?>[] paramTypes, Object[] paramValues) {
        try {
            T instance = (T) UNSAFE.allocateInstance(enumClass);
            Field nameField = Enum.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(instance, name);
            Field ordinalField = Enum.class.getDeclaredField("ordinal");
            ordinalField.setAccessible(true);
            ordinalField.set(instance, ordinal);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create enum " + name, e);
        }
    }

    public static void addEnums(Class<?> enumClass, Object... enumValues) {
        try {
            Field valuesField = enumClass.getDeclaredField("$VALUES");
            valuesField.setAccessible(true);
            Object[] oldValues = (Object[]) valuesField.get(null);
            Object[] newValues = Arrays.copyOf(oldValues, oldValues.length + enumValues.length);
            System.arraycopy(enumValues, 0, newValues, oldValues.length, enumValues.length);
            valuesField.set(null, newValues);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add enum values to " + enumClass, e);
        }
    }
}
