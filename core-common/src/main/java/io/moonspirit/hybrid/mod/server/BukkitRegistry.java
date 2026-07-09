package io.moonspirit.hybrid.mod.server;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BukkitRegistry {

    public static final Map<Material, Block> MATERIAL_TO_BLOCK = new HashMap<>();
    public static final Map<Block, Material> BLOCK_TO_MATERIAL = new HashMap<>();
    private static final Map<net.minecraft.world.entity.EntityType<?>, org.bukkit.entity.EntityType> entityTypeMap = new HashMap<>();

    public static void registerAll(RegistryAccess registryAccess) {
        System.out.println("[Hybrid] Registering Bukkit registry bridge...");
        registerMaterials();
        registerEntityTypes();
        registerBiomes();
    }

    private static void registerMaterials() {
        for (Block block : BuiltInRegistries.BLOCK) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            if (key == null) continue;
            String name = key.getPath().toUpperCase(Locale.ROOT);
            Material mat = Material.getMaterial(name);
            if (mat != null && mat.isBlock()) {
                MATERIAL_TO_BLOCK.put(mat, block);
                BLOCK_TO_MATERIAL.put(block, mat);
            }
        }
        System.out.println("[Hybrid] Registered " + MATERIAL_TO_BLOCK.size() + " block mappings");
        System.out.println("[Hybrid] Registered " + BuiltInRegistries.ITEM.keySet().size() + " items");
    }

    private static void registerEntityTypes() {
        long count = BuiltInRegistries.ENTITY_TYPE.keySet().size();
        for (net.minecraft.world.entity.EntityType<?> mcType : BuiltInRegistries.ENTITY_TYPE) {
            ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(mcType);
            if (key == null) continue;
            String mcKey = key.getPath();
            String bukkitName = mcKey.toUpperCase(Locale.ROOT);
            try {
                org.bukkit.entity.EntityType bukkitType = org.bukkit.entity.EntityType.valueOf(bukkitName);
                entityTypeMap.put(mcType, bukkitType);
            } catch (IllegalArgumentException e) {
                try {
                    org.bukkit.entity.EntityType bukkitType = org.bukkit.entity.EntityType.fromName(mcKey);
                    if (bukkitType != null) {
                        entityTypeMap.put(mcType, bukkitType);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        System.out.println("[Hybrid] Registered " + count + " entity types, mapped " + entityTypeMap.size());
    }

    private static void registerBiomes() {
        System.out.println("[Hybrid] Biomes available via RegistryAccess");
    }

    public static org.bukkit.entity.EntityType entityTypeFromMinecraft(net.minecraft.world.entity.EntityType<?> mcType) {
        return entityTypeMap.get(mcType);
    }
}
