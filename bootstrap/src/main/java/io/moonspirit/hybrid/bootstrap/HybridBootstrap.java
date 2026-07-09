package io.moonspirit.hybrid.bootstrap;

import java.io.File;

public class HybridBootstrap {
    public static void main(String[] args) {
        System.out.println("[Hybrid] Starting Fabric Hybrid Server...");
        System.out.println("[Hybrid] Version: 1.0.0");

        // Fabric Loader 会在类路径上自动初始化
        // 实际启动由 fabric.mod.json 的 entrypoint 处理

        try {
            // 启动 Fabric Loader -> Minecraft Server
            Class<?> mainClass = Class.forName("net.fabricmc.loader.impl.launch.knot.KnotServer");
            var mainMethod = mainClass.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[]{args});
        } catch (Exception e) {
            System.err.println("[Hybrid] Failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
