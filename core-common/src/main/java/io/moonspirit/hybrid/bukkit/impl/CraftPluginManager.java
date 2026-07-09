package io.moonspirit.hybrid.bukkit.impl;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class CraftPluginManager implements PluginManager {
    private final Map<String, Plugin> plugins = new LinkedHashMap<>();
    private final Map<String, Permission> permissions = new HashMap<>();
    private final Map<String, Set<Permissible>> permissionSubscriptions = new HashMap<>();
    private final Map<Boolean, Set<Permissible>> defaultPermSubscriptions = new HashMap<>();
    private final File pluginDirectory = new File("plugins");

    @Override
    public Plugin getPlugin(String name) {
        return plugins.get(name);
    }

    @Override
    public Plugin[] getPlugins() {
        return plugins.values().toArray(new Plugin[0]);
    }

    @Override
    public boolean isPluginEnabled(String name) {
        Plugin plugin = plugins.get(name);
        return plugin != null && plugin.isEnabled();
    }

    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return plugin != null && plugin.isEnabled();
    }

    public void loadPlugins() {
        if (!pluginDirectory.exists()) {
            pluginDirectory.mkdirs();
            return;
        }

        File[] files = pluginDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try {
                loadPlugin(file);
            } catch (Exception e) {
                System.err.println("[Hybrid] Failed to load " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    @Override
    public Plugin loadPlugin(File file) throws InvalidPluginException, UnknownDependencyException, InvalidDescriptionException {
        try {
            HybridPluginClassLoader loader = new HybridPluginClassLoader(this, file, getClass().getClassLoader());
            JavaPlugin plugin = loader.getPlugin();
            plugins.put(plugin.getName(), plugin);
            return plugin;
        } catch (Exception e) {
            throw new InvalidPluginException("Failed to load " + file.getName(), e);
        }
    }

    @Override
    public Plugin[] loadPlugins(File file) {
        loadPlugins();
        return getPlugins();
    }

    @Override
    public void disablePlugins() {
        plugins.values().forEach(this::disablePlugin);
    }

    @Override
    public void clearPlugins() {
        plugins.clear();
    }

    @Override
    public void callEvent(Event event) {
    }

    @Override
    public void registerEvents(Listener listener, Plugin plugin) {
    }

    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin) {
    }

    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin, boolean ignoreCancelled) {
    }

    @Override
    public void enablePlugin(Plugin plugin) {
        if (plugin.isEnabled()) return;
        try {
            plugin.onEnable();
        } catch (Throwable t) {
            System.err.println("[Hybrid] Error enabling " + plugin.getName() + ": " + t.getMessage());
        }
    }

    @Override
    public void disablePlugin(Plugin plugin) {
        if (plugin.isEnabled()) {
            try {
                plugin.onDisable();
            } catch (Throwable t) {
                System.err.println("[Hybrid] Error disabling " + plugin.getName() + ": " + t.getMessage());
            }
        }
    }

    @Override
    public Permission getPermission(String name) {
        return permissions.get(name);
    }

    @Override
    public void addPermission(Permission perm) {
        permissions.put(perm.getName(), perm);
    }

    @Override
    public void removePermission(Permission perm) {
        permissions.remove(perm.getName());
    }

    @Override
    public void removePermission(String name) {
        permissions.remove(name);
    }

    @Override
    public Set<Permission> getDefaultPermissions(boolean op) {
        return Collections.emptySet();
    }

    @Override
    public void recalculatePermissionDefaults(Permission perm) {
    }

    @Override
    public void subscribeToPermission(String permission, Permissible permissible) {
        permissionSubscriptions.computeIfAbsent(permission, k -> new HashSet<>()).add(permissible);
    }

    @Override
    public void unsubscribeFromPermission(String permission, Permissible permissible) {
        Set<Permissible> subs = permissionSubscriptions.get(permission);
        if (subs != null) {
            subs.remove(permissible);
            if (subs.isEmpty()) {
                permissionSubscriptions.remove(permission);
            }
        }
    }

    @Override
    public Set<Permissible> getPermissionSubscriptions(String permission) {
        return permissionSubscriptions.getOrDefault(permission, Collections.emptySet());
    }

    @Override
    public void subscribeToDefaultPerms(boolean op, Permissible permissible) {
        defaultPermSubscriptions.computeIfAbsent(op, k -> new HashSet<>()).add(permissible);
    }

    @Override
    public void unsubscribeFromDefaultPerms(boolean op, Permissible permissible) {
        Set<Permissible> subs = defaultPermSubscriptions.get(op);
        if (subs != null) {
            subs.remove(permissible);
            if (subs.isEmpty()) {
                defaultPermSubscriptions.remove(op);
            }
        }
    }

    @Override
    public Set<Permissible> getDefaultPermSubscriptions(boolean op) {
        return defaultPermSubscriptions.getOrDefault(op, Collections.emptySet());
    }

    @Override
    public Set<Permission> getPermissions() {
        return new HashSet<>(permissions.values());
    }

    @Override
    public boolean useTimings() {
        return false;
    }

    @Override
    public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException {
    }
}
