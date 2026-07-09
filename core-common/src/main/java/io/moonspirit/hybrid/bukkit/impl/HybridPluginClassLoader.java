package io.moonspirit.hybrid.bukkit.impl;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class HybridPluginClassLoader extends URLClassLoader {
    private final File file;
    private final CraftPluginManager manager;
    private JavaPlugin plugin;

    static {
        ClassLoader.registerAsParallelCapable();
    }

    public HybridPluginClassLoader(CraftPluginManager manager, File file, ClassLoader parent) throws InvalidPluginException {
        super(new URL[]{}, parent);
        this.manager = manager;
        this.file = file;

        try {
            URL jarUrl = file.toURI().toURL();
            addURL(jarUrl);

            try (JarFile jar = new JarFile(file)) {
                JarEntry entry = jar.getJarEntry("plugin.yml");
                if (entry == null) throw new InvalidPluginException("No plugin.yml in " + file.getName());

                InputStream in = jar.getInputStream(entry);
                YamlConfiguration config = new YamlConfiguration();
                config.load(new InputStreamReader(in));

                String mainClass = config.getString("main");
                if (mainClass == null) throw new InvalidPluginException("No main class in plugin.yml");

                String pluginName = config.getString("name", file.getName());
                String version = config.getString("version", "1.0");

                Class<?> mainClazz = Class.forName(mainClass, true, this);

                if (!JavaPlugin.class.isAssignableFrom(mainClazz)) {
                    throw new InvalidPluginException("Main class must extend JavaPlugin");
                }

                plugin = (JavaPlugin) mainClazz.getDeclaredConstructor().newInstance();

                var descField = JavaPlugin.class.getDeclaredField("description");
                descField.setAccessible(true);
                var mockDescription = new PluginDescriptionFile(pluginName, version, mainClass);
                descField.set(plugin, mockDescription);

                var dataFolderField = JavaPlugin.class.getDeclaredField("dataFolder");
                dataFolderField.setAccessible(true);
                dataFolderField.set(plugin, new File("plugins/" + pluginName));
                plugin.getDataFolder().mkdirs();
            }
        } catch (Exception e) {
            throw new InvalidPluginException("Failed to load plugin: " + file.getName(), e);
        }
    }

    public JavaPlugin getPlugin() { return plugin; }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            return super.loadClass(name, resolve);
        } catch (ClassNotFoundException e) {
            return getParent().loadClass(name);
        }
    }
}
