package io.moonspirit.hybrid.bukkit.impl;

import io.moonspirit.hybrid.mod.server.HybridServer;
import net.minecraft.server.MinecraftServer;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimpleServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.packs.DataPackManager;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.CachedServerIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class CraftServer implements Server {
    private final HybridServer hybridServer;
    private final MinecraftServer console;
    private final CraftScheduler scheduler = new CraftScheduler();
    private final ServicesManager servicesManager = new SimpleServicesManager();
    private final Messenger messenger = new StandardMessenger();
    private final CraftPluginManager pluginManager = new CraftPluginManager();
    private String motd;
    private int maxPlayers = 20;

    public CraftServer(HybridServer hybridServer, MinecraftServer console) {
        this.hybridServer = hybridServer;
        this.console = console;
        this.motd = "A Fabric Hybrid Server";
    }

    public MinecraftServer getHandle() { return console; }

    @Override
    public String getName() { return "Fabric-Hybrid"; }

    @Override
    public String getVersion() { return "1.0.0"; }

    @Override
    public String getBukkitVersion() { return "1.20.1-R0.1-SNAPSHOT"; }

    @Override
    public Collection<? extends Player> getOnlinePlayers() { return Collections.emptyList(); }

    @Override
    public int getMaxPlayers() { return maxPlayers; }

    @Override
    public void setMaxPlayers(int max) { this.maxPlayers = max; }

    @Override
    public int getPort() { return 25565; }

    @Override
    public int getViewDistance() { return 8; }

    @Override
    public int getSimulationDistance() { return 8; }

    @Override
    public String getIp() { return "0.0.0.0"; }

    @Override
    public String getWorldType() { return "default"; }

    @Override
    public boolean getGenerateStructures() { return true; }

    @Override
    public int getMaxWorldSize() { return 29999984; }

    @Override
    public boolean getAllowEnd() { return true; }

    @Override
    public boolean getAllowNether() { return true; }

    @Override
    public List<String> getInitialEnabledPacks() { return Collections.emptyList(); }

    @Override
    public List<String> getInitialDisabledPacks() { return Collections.emptyList(); }

    @Override
    public DataPackManager getDataPackManager() { return null; }

    @Override
    public String getResourcePack() { return ""; }

    @Override
    public String getResourcePackHash() { return ""; }

    @Override
    public String getResourcePackPrompt() { return ""; }

    @Override
    public boolean isResourcePackRequired() { return false; }

    @Override
    public boolean hasWhitelist() { return false; }

    @Override
    public void setWhitelist(boolean value) { }

    @Override
    public boolean isWhitelistEnforced() { return false; }

    @Override
    public void setWhitelistEnforced(boolean value) { }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() { return Collections.emptySet(); }

    @Override
    public void reloadWhitelist() { }

    @Override
    public int broadcastMessage(String message) { return 0; }

    @Override
    public String getUpdateFolder() { return "update"; }

    @Override
    public File getUpdateFolderFile() { return null; }

    @Override
    public long getConnectionThrottle() { return 0L; }

    @Override
    public int getTicksPerAnimalSpawns() { return 400; }

    @Override
    public int getTicksPerMonsterSpawns() { return 400; }

    @Override
    public int getTicksPerWaterSpawns() { return 1; }

    @Override
    public int getTicksPerWaterAmbientSpawns() { return 1; }

    @Override
    public int getTicksPerWaterUndergroundCreatureSpawns() { return 1; }

    @Override
    public int getTicksPerAmbientSpawns() { return 1; }

    @Override
    public int getTicksPerSpawns(SpawnCategory category) { return 1; }

    @Override
    public Player getPlayer(String name) { return null; }

    @Override
    public Player getPlayerExact(String name) { return null; }

    @Override
    public List<Player> matchPlayer(String name) { return Collections.emptyList(); }

    @Override
    public Player getPlayer(UUID uuid) { return null; }

    @Override
    public PluginManager getPluginManager() { return pluginManager; }

    @Override
    public BukkitScheduler getScheduler() { return scheduler; }

    @Override
    public ServicesManager getServicesManager() { return servicesManager; }

    @Override
    public List<World> getWorlds() { return Collections.emptyList(); }

    @Override
    public World createWorld(WorldCreator creator) { return null; }

    @Override
    public boolean unloadWorld(String name, boolean save) { return false; }

    @Override
    public boolean unloadWorld(World world, boolean save) { return false; }

    @Override
    public World getWorld(String name) { return null; }

    @Override
    public World getWorld(UUID uuid) { return null; }

    @Override
    public WorldBorder createWorldBorder() { return null; }

    @Override
    public MapView getMap(int id) { return null; }

    @Override
    public MapView createMap(World world) { return null; }

    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType) { return null; }

    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType, int radius, boolean findUnexplored) { return null; }

    @Override
    public void reload() { }

    @Override
    public void reloadData() { }

    @Override
    public Logger getLogger() { return Logger.getLogger("Minecraft"); }

    @Override
    public PluginCommand getPluginCommand(String name) { return null; }

    @Override
    public void savePlayers() { }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException { return false; }

    @Override
    public boolean addRecipe(Recipe recipe) { return false; }

    @Override
    public List<Recipe> getRecipesFor(ItemStack result) { return Collections.emptyList(); }

    @Override
    public Recipe getRecipe(NamespacedKey key) { return null; }

    @Override
    public Recipe getCraftingRecipe(ItemStack[] ingredients, World world) { return null; }

    @Override
    public ItemStack craftItem(ItemStack[] ingredients, World world, Player player) { return null; }

    @Override
    public Iterator<Recipe> recipeIterator() { return Collections.emptyIterator(); }

    @Override
    public void clearRecipes() { }

    @Override
    public void resetRecipes() { }

    @Override
    public boolean removeRecipe(NamespacedKey key) { return false; }

    @Override
    public Map<String, String[]> getCommandAliases() { return Collections.emptyMap(); }

    @Override
    public int getSpawnRadius() { return 0; }

    @Override
    public void setSpawnRadius(int value) { }

    @Override
    public boolean shouldSendChatPreviews() { return false; }

    @Override
    public boolean isEnforcingSecureProfiles() { return false; }

    @Override
    public boolean getHideOnlinePlayers() { return false; }

    @Override
    public boolean getOnlineMode() { return false; }

    @Override
    public boolean getAllowFlight() { return false; }

    @Override
    public boolean isHardcore() { return false; }

    @Override
    public void shutdown() { }

    @Override
    public int broadcast(String message, String permission) { return 0; }

    @Override
    public OfflinePlayer getOfflinePlayer(String name) { return null; }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID uuid) { return null; }

    @Override
    public PlayerProfile createPlayerProfile(UUID uuid, String name) { return null; }

    @Override
    public PlayerProfile createPlayerProfile(UUID uuid) { return null; }

    @Override
    public PlayerProfile createPlayerProfile(String name) { return null; }

    @Override
    public Set<String> getIPBans() { return Collections.emptySet(); }

    @Override
    public void banIP(String address) { }

    @Override
    public void unbanIP(String address) { }

    @Override
    public void banIP(InetAddress address) { }

    @Override
    public void unbanIP(InetAddress address) { }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() { return Collections.emptySet(); }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BanList<?>> T getBanList(BanList.Type type) { return null; }

    @Override
    public Set<OfflinePlayer> getOperators() { return Collections.emptySet(); }

    @Override
    public GameMode getDefaultGameMode() { return GameMode.SURVIVAL; }

    @Override
    public void setDefaultGameMode(GameMode mode) { }

    @Override
    public ConsoleCommandSender getConsoleSender() { return null; }

    @Override
    public File getWorldContainer() { return null; }

    @Override
    public OfflinePlayer[] getOfflinePlayers() { return new OfflinePlayer[0]; }

    @Override
    public Messenger getMessenger() { return messenger; }

    @Override
    public HelpMap getHelpMap() { return null; }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) { return null; }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) { return null; }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException { return null; }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException { return null; }

    @Override
    public Merchant createMerchant(String title) { return null; }

    @Override
    public int getMaxChainedNeighborUpdates() { return 0; }

    @Override
    public int getMonsterSpawnLimit() { return 0; }

    @Override
    public int getAnimalSpawnLimit() { return 0; }

    @Override
    public int getWaterAnimalSpawnLimit() { return 0; }

    @Override
    public int getWaterAmbientSpawnLimit() { return 0; }

    @Override
    public int getWaterUndergroundCreatureSpawnLimit() { return 0; }

    @Override
    public int getAmbientSpawnLimit() { return 0; }

    @Override
    public int getSpawnLimit(SpawnCategory category) { return 0; }

    @Override
    public boolean isPrimaryThread() { return Thread.currentThread() == getServerThread(); }

    private Thread getServerThread() {
        return hybridServer.getMinecraftServer().getRunningThread();
    }

    @Override
    public String getMotd() { return motd; }

    @Override
    public void setMotd(String motd) { this.motd = motd; }

    @Override
    public String getShutdownMessage() { return null; }

    @Override
    public Warning.WarningState getWarningState() { return Warning.WarningState.DEFAULT; }

    @Override
    public ItemFactory getItemFactory() { return null; }

    @Override
    public ScoreboardManager getScoreboardManager() { return null; }

    @Override
    public Criteria getScoreboardCriteria(String name) { return null; }

    @Override
    public CachedServerIcon getServerIcon() { return null; }

    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception { return null; }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException, Exception { return null; }

    @Override
    public void setIdleTimeout(int timeout) { }

    @Override
    public int getIdleTimeout() { return 0; }

    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) { return null; }

    @Override
    public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) { return null; }

    @Override
    public KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor color, BarStyle style, BarFlag... flags) { return null; }

    @Override
    public Iterator<KeyedBossBar> getBossBars() { return Collections.emptyIterator(); }

    @Override
    public KeyedBossBar getBossBar(NamespacedKey key) { return null; }

    @Override
    public boolean removeBossBar(NamespacedKey key) { return false; }

    @Override
    public Entity getEntity(UUID uuid) { return null; }

    @Override
    public Advancement getAdvancement(NamespacedKey key) { return null; }

    @Override
    public Iterator<Advancement> advancementIterator() { return Collections.emptyIterator(); }

    @Override
    public BlockData createBlockData(Material material) { return null; }

    @Override
    public BlockData createBlockData(Material material, Consumer<BlockData> consumer) { return null; }

    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException { return null; }

    @Override
    public BlockData createBlockData(Material material, String data) throws IllegalArgumentException { return null; }

    @Override
    public <T extends Keyed> Tag<T> getTag(String registry, NamespacedKey key, Class<T> clazz) { return null; }

    @Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(String registry, Class<T> clazz) { return Collections.emptyList(); }

    @Override
    public LootTable getLootTable(NamespacedKey key) { return null; }

    @Override
    public List<Entity> selectEntities(CommandSender sender, String selector) throws IllegalArgumentException { return Collections.emptyList(); }

    @Override
    public StructureManager getStructureManager() { return null; }

    @Override
    public <T extends Keyed> Registry<T> getRegistry(Class<T> clazz) { return null; }

    @Override
    public UnsafeValues getUnsafe() { return null; }

    @Override
    public Spigot spigot() { return null; }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) { }

    @Override
    public Set<String> getListeningPluginChannels() { return Collections.emptySet(); }
}
