package io.moonspirit.hybrid.bootstrap;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.lang.reflect.Method;

public class HybridBootstrap {
    private static final String MINECRAFT_VERSION = "1.20.1";
    private static final String FABRIC_LOADER_VERSION = "0.16.10";
    private static final File LIBRARIES_DIR = new File("libraries");
    private static final File MINECRAFT_JAR = new File(LIBRARIES_DIR, "minecraft-server-" + MINECRAFT_VERSION + ".jar");
    private static final File FABRIC_LOADER_JAR = new File(LIBRARIES_DIR, "fabric-loader-" + FABRIC_LOADER_VERSION + ".jar");
    
    public static void main(String[] args) throws Exception {
        System.out.println("[Hybrid] Fabric Hybrid Server 1.0.0");
        System.out.println("[Hybrid] Target: Minecraft " + MINECRAFT_VERSION + " + Fabric Loader " + FABRIC_LOADER_VERSION);
        
        LIBRARIES_DIR.mkdirs();
        
        if (!MINECRAFT_JAR.exists()) {
            System.out.println("[Hybrid] Downloading Minecraft " + MINECRAFT_VERSION + " server...");
            downloadMinecraftServer();
        }
        
        if (!FABRIC_LOADER_JAR.exists()) {
            System.out.println("[Hybrid] Downloading Fabric Loader " + FABRIC_LOADER_VERSION + "...");
            downloadFabricLoader();
        }
        
        System.out.println("[Hybrid] Starting server...");
        startServer(args);
    }
    
    private static void downloadMinecraftServer() throws Exception {
        String manifestJson = readUrl("https://launchermeta.mojang.com/mc/game/version_manifest_v2.json");
        
        String versionKey = "\"id\":\"" + MINECRAFT_VERSION + "\"";
        int versionIdx = manifestJson.indexOf(versionKey);
        if (versionIdx < 0) throw new RuntimeException("Minecraft version " + MINECRAFT_VERSION + " not found");
        
        String urlKey = "\"url\":\"";
        int urlStart = manifestJson.indexOf(urlKey, versionIdx) + urlKey.length();
        int urlEnd = manifestJson.indexOf("\"", urlStart);
        String versionUrl = manifestJson.substring(urlStart, urlEnd);
        
        String versionJson = readUrl(versionUrl);
        
        String serverUrlKey = "\"server\":{\"sha1\":\"";
        int serverIdx = versionJson.indexOf(serverUrlKey);
        if (serverIdx < 0) throw new RuntimeException("Server download not found");
        
        String downloadKey = "\"url\":\"";
        int dlStart = versionJson.indexOf(downloadKey, serverIdx) + downloadKey.length();
        int dlEnd = versionJson.indexOf("\"", dlStart);
        String serverUrl = versionJson.substring(dlStart, dlEnd);
        
        System.out.println("[Hybrid] Downloading from: " + serverUrl);
        downloadFile(serverUrl, MINECRAFT_JAR);
        System.out.println("[Hybrid] Minecraft server downloaded: " + MINECRAFT_JAR.getName());
    }
    
    private static void downloadFabricLoader() throws Exception {
        String url = "https://maven.fabricmc.net/net/fabricmc/fabric-loader/"
            + FABRIC_LOADER_VERSION + "/fabric-loader-" + FABRIC_LOADER_VERSION + ".jar";
        System.out.println("[Hybrid] Downloading from: " + url);
        downloadFile(url, FABRIC_LOADER_JAR);
        System.out.println("[Hybrid] Fabric Loader downloaded: " + FABRIC_LOADER_JAR.getName());
    }
    
    private static String readUrl(String urlStr) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(urlStr).openStream()))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
        }
        return sb.toString();
    }
    
    private static void downloadFile(String urlStr, File target) throws Exception {
        Path temp = target.toPath().resolveSibling(target.getName() + ".tmp");
        try (InputStream in = new URL(urlStr).openStream()) {
            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
        }
        Files.move(temp, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    private static void startServer(String[] args) throws Exception {
        File ourJar = new File(HybridBootstrap.class.getProtectionDomain()
            .getCodeSource().getLocation().toURI());
        
        URLClassLoader loader = new URLClassLoader(new URL[]{
            ourJar.toURI().toURL(),
            MINECRAFT_JAR.toURI().toURL(),
            FABRIC_LOADER_JAR.toURI().toURL()
        }, ClassLoader.getSystemClassLoader());
        
        Thread.currentThread().setContextClassLoader(loader);
        
        Class<?> knotServer = loader.loadClass("net.fabricmc.loader.impl.launch.knot.KnotServer");
        Method main = knotServer.getMethod("main", String[].class);
        main.invoke(null, new Object[]{args});
    }
}
