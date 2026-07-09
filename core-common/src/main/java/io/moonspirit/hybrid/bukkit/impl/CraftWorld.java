package io.moonspirit.hybrid.bukkit.impl;

import io.moonspirit.hybrid.bukkit.impl.block.CraftBlockState;
import io.moonspirit.hybrid.bukkit.impl.block.CraftChest;
import io.moonspirit.hybrid.bukkit.impl.block.CraftFurnace;
import io.moonspirit.hybrid.bukkit.impl.block.CraftSign;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftEntity;
import io.moonspirit.hybrid.bukkit.impl.entity.CraftLivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameRule;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Raid;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.structure.Structure;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.StructureSearchResult;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class CraftWorld implements World {

    private final ServerLevel handle;
    private final CraftServer craftServer;
    private final UUID uuid = UUID.randomUUID();

    public CraftWorld(ServerLevel handle, CraftServer craftServer) {
        this.handle = handle;
        this.craftServer = craftServer;
    }

    public ServerLevel getHandle() {
        return handle;
    }

    public CraftServer getCraftServer() {
        return craftServer;
    }

    @Override
    public String getName() {
        return handle.dimension().location().toString();
    }

    @Override
    public UUID getUID() {
        return uuid;
    }

    @Override
    public NamespacedKey getKey() {
        ResourceKey<Level> dimKey = handle.dimension();
        return NamespacedKey.minecraft(dimKey.location().getPath());
    }

    @Override
    public Environment getEnvironment() {
        ResourceKey<Level> dim = handle.dimension();
        if (dim.equals(Level.OVERWORLD)) return Environment.NORMAL;
        if (dim.equals(Level.NETHER)) return Environment.NETHER;
        if (dim.equals(Level.END)) return Environment.THE_END;
        return Environment.CUSTOM;
    }

    @Override
    public long getSeed() {
        return handle.getSeed();
    }

    @Override
    public int getMinHeight() {
        return handle.getMinBuildHeight();
    }

    @Override
    public int getMaxHeight() {
        return handle.getMaxBuildHeight();
    }

    @Override
    public int getLogicalHeight() {
        return handle.getLogicalHeight();
    }

    // ===== Block access =====

    @Override
    public Block getBlockAt(int x, int y, int z) {
        return new CraftBlock(this, new BlockPos(x, y, z));
    }

    @Override
    public Block getBlockAt(Location location) {
        return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Block getHighestBlockAt(int x, int z) {
        return getBlockAt(x, getHighestBlockYAt(x, z), z);
    }

    @Override
    public Block getHighestBlockAt(Location location) {
        return getHighestBlockAt(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public Block getHighestBlockAt(int x, int z, HeightMap heightMap) {
        return getBlockAt(x, getHighestBlockYAt(x, z, heightMap), z);
    }

    @Override
    public Block getHighestBlockAt(Location location, HeightMap heightMap) {
        return getHighestBlockAt(location.getBlockX(), location.getBlockZ(), heightMap);
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        return handle.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
    }

    @Override
    public int getHighestBlockYAt(Location location) {
        return getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public int getHighestBlockYAt(int x, int z, HeightMap heightMap) {
        Heightmap.Types nmsType;
        switch (heightMap) {
            case MOTION_BLOCKING: nmsType = Heightmap.Types.MOTION_BLOCKING; break;
            case MOTION_BLOCKING_NO_LEAVES: nmsType = Heightmap.Types.MOTION_BLOCKING_NO_LEAVES; break;
            case OCEAN_FLOOR: nmsType = Heightmap.Types.OCEAN_FLOOR; break;
            case OCEAN_FLOOR_WG: nmsType = Heightmap.Types.OCEAN_FLOOR_WG; break;
            case WORLD_SURFACE: nmsType = Heightmap.Types.WORLD_SURFACE; break;
            case WORLD_SURFACE_WG: nmsType = Heightmap.Types.WORLD_SURFACE_WG; break;
            default: nmsType = Heightmap.Types.MOTION_BLOCKING;
        }
        return handle.getHeight(nmsType, x, z);
    }

    @Override
    public int getHighestBlockYAt(Location location, HeightMap heightMap) {
        return getHighestBlockYAt(location.getBlockX(), location.getBlockZ(), heightMap);
    }

    // ===== Chunk access =====

    @Override
    public Chunk getChunkAt(int x, int z) {
        return getChunkAt(x, z, true);
    }

    @Override
    public Chunk getChunkAt(int x, int z, boolean generate) {
        LevelChunk levelChunk = handle.getChunkSource().getChunk(x, z, generate);
        if (levelChunk == null) return null;
        return new CraftChunk(levelChunk, this);
    }

    @Override
    public Chunk getChunkAt(Location location) {
        return getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

    @Override
    public Chunk getChunkAt(Block block) {
        return getChunkAt(block.getX() >> 4, block.getZ() >> 4);
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        return isChunkLoaded(chunk.getX(), chunk.getZ());
    }

    @Override
    public Chunk[] getLoadedChunks() {
        return new Chunk[0];
    }

    @Override
    public void loadChunk(Chunk chunk) {
        loadChunk(chunk.getX(), chunk.getZ());
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return handle.getChunkSource().hasChunk(x, z);
    }

    @Override
    public boolean isChunkGenerated(int x, int z) {
        return isChunkLoaded(x, z);
    }

    @Override
    public boolean isChunkInUse(int x, int z) {
        return false;
    }

    @Override
    public void loadChunk(int x, int z) {
        loadChunk(x, z, true);
    }

    @Override
    public boolean loadChunk(int x, int z, boolean generate) {
        handle.getChunkSource().getChunk(x, z, generate);
        return true;
    }

    @Override
    public boolean unloadChunk(Chunk chunk) {
        return unloadChunk(chunk.getX(), chunk.getZ());
    }

    @Override
    public boolean unloadChunk(int x, int z) {
        return unloadChunk(x, z, true);
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save) {
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int x, int z) {
        return false;
    }

    @Override
    public boolean regenerateChunk(int x, int z) {
        return false;
    }

    @Override
    public boolean refreshChunk(int x, int z) {
        return false;
    }

    @Override
    public boolean isChunkForceLoaded(int x, int z) {
        return false;
    }

    @Override
    public void setChunkForceLoaded(int x, int z, boolean forced) {
    }

    @Override
    public Collection<Chunk> getForceLoadedChunks() {
        return Collections.emptyList();
    }

    @Override
    public boolean addPluginChunkTicket(int x, int z, Plugin plugin) {
        return false;
    }

    @Override
    public boolean removePluginChunkTicket(int x, int z, Plugin plugin) {
        return false;
    }

    @Override
    public void removePluginChunkTickets(Plugin plugin) {
    }

    @Override
    public Collection<Plugin> getPluginChunkTickets(int x, int z) {
        return Collections.emptyList();
    }

    @Override
    public Map<Plugin, Collection<Chunk>> getPluginChunkTickets() {
        return Collections.emptyMap();
    }

    // ===== Entity access =====

    @Override
    public List<Entity> getEntities() {
        List<Entity> result = new ArrayList<>();
        net.minecraft.world.level.entity.EntityTypeTest<net.minecraft.world.entity.Entity, net.minecraft.world.entity.Entity> typeTest =
            net.minecraft.world.level.entity.EntityTypeTest.forClass(net.minecraft.world.entity.Entity.class);
        net.minecraft.world.phys.AABB aabb = new net.minecraft.world.phys.AABB(
            Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
            Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (net.minecraft.world.entity.Entity entity : handle.getEntities(typeTest, aabb, e -> true)) {
            result.add(new CraftEntity(entity));
        }
        return result;
    }

    @Override
    public List<LivingEntity> getLivingEntities() {
        List<LivingEntity> result = new ArrayList<>();
        net.minecraft.world.level.entity.EntityTypeTest<net.minecraft.world.entity.Entity, net.minecraft.world.entity.Entity> typeTest =
            net.minecraft.world.level.entity.EntityTypeTest.forClass(net.minecraft.world.entity.Entity.class);
        net.minecraft.world.phys.AABB aabb = new net.minecraft.world.phys.AABB(
            Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
            Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (net.minecraft.world.entity.Entity entity : handle.getEntities(typeTest, aabb, e -> true)) {
            if (entity instanceof net.minecraft.world.entity.LivingEntity) {
                result.add(new CraftLivingEntity((net.minecraft.world.entity.LivingEntity) entity));
            }
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
        return Collections.emptyList();
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> clazz) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
        return Collections.emptyList();
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> result = new ArrayList<>();
        for (net.minecraft.server.level.ServerPlayer player : handle.getPlayers(p -> true)) {
            result.add(new CraftPlayer(player));
        }
        return result;
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z, Predicate<Entity> filter) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Entity> getNearbyEntities(BoundingBox boundingBox) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Entity> getNearbyEntities(BoundingBox boundingBox, Predicate<Entity> filter) {
        return Collections.emptyList();
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, double raySize) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, Predicate<Entity> filter) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, double raySize, Predicate<Entity> filter) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlock) {
        return null;
    }

    @Override
    public RayTraceResult rayTrace(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlock, double raySize, Predicate<Entity> filter) {
        return null;
    }

    // ===== Spawn =====

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
        return null;
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz, org.bukkit.util.Consumer<T> function) throws IllegalArgumentException {
        return null;
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz, boolean randomizeData, org.bukkit.util.Consumer<T> function) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Entity spawnEntity(Location location, org.bukkit.entity.EntityType entityType) {
        return null;
    }

    @Override
    public Entity spawnEntity(Location location, org.bukkit.entity.EntityType entityType, boolean randomizeData) {
        return null;
    }

    @Override
    public Item dropItem(Location location, ItemStack item) {
        return null;
    }

    @Override
    public Item dropItem(Location location, ItemStack item, org.bukkit.util.Consumer<Item> function) {
        return null;
    }

    @Override
    public Item dropItemNaturally(Location location, ItemStack item) {
        return null;
    }

    @Override
    public Item dropItemNaturally(Location location, ItemStack item, org.bukkit.util.Consumer<Item> function) {
        return null;
    }

    @Override
    public Arrow spawnArrow(Location location, Vector direction, float speed, float spread) {
        return null;
    }

    @Override
    public <T extends AbstractArrow> T spawnArrow(Location location, Vector direction, float speed, float spread, Class<T> clazz) {
        return null;
    }

    @Override
    public boolean generateTree(Location location, TreeType treeType) {
        return false;
    }

    @Override
    public boolean generateTree(Location location, TreeType treeType, org.bukkit.BlockChangeDelegate delegate) {
        return false;
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType) {
        return false;
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType, org.bukkit.util.Consumer<BlockState> consumer) {
        return false;
    }

    @Override
    public boolean generateTree(Location location, Random random, TreeType treeType, Predicate<BlockState> predicate) {
        return false;
    }

    @Override
    public LightningStrike strikeLightning(Location location) {
        return null;
    }

    @Override
    public LightningStrike strikeLightningEffect(Location location) {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, MaterialData data) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, BlockData data) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, Material material, byte data) throws IllegalArgumentException {
        return null;
    }

    // ===== Time =====

    @Override
    public long getTime() {
        return handle.getDayTime() % 24000L;
    }

    @Override
    public void setTime(long time) {
        long dayTime = handle.getDayTime() - (handle.getDayTime() % 24000L) + time;
        handle.setDayTime(dayTime);
    }

    @Override
    public long getFullTime() {
        return handle.getDayTime();
    }

    @Override
    public void setFullTime(long time) {
        handle.setDayTime(time);
    }

    @Override
    public long getGameTime() {
        return handle.getGameTime();
    }

    // ===== Weather =====

    @Override
    public boolean hasStorm() {
        return handle.isRaining();
    }

    @Override
    public void setStorm(boolean hasStorm) {
    }

    @Override
    public int getWeatherDuration() {
        return 0;
    }

    @Override
    public void setWeatherDuration(int duration) {
    }

    @Override
    public boolean isThundering() {
        return handle.isThundering();
    }

    @Override
    public void setThundering(boolean thundering) {
    }

    @Override
    public int getThunderDuration() {
        return 0;
    }

    @Override
    public void setThunderDuration(int duration) {
    }

    @Override
    public boolean isClearWeather() {
        return !hasStorm() && !isThundering();
    }

    @Override
    public void setClearWeatherDuration(int duration) {
    }

    @Override
    public int getClearWeatherDuration() {
        return 0;
    }

    // ===== Explosions =====

    @Override
    public boolean createExplosion(double x, double y, double z, float power) {
        return false;
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
        return false;
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
        return false;
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks, Entity source) {
        return false;
    }

    @Override
    public boolean createExplosion(Location location, float power) {
        return false;
    }

    @Override
    public boolean createExplosion(Location location, float power, boolean setFire) {
        return false;
    }

    @Override
    public boolean createExplosion(Location location, float power, boolean setFire, boolean breakBlocks) {
        return false;
    }

    @Override
    public boolean createExplosion(Location location, float power, boolean setFire, boolean breakBlocks, Entity source) {
        return false;
    }

    // ===== Game rules =====

    @Override
    public String[] getGameRules() {
        return new String[0];
    }

    @Override
    public String getGameRuleValue(String rule) {
        return "";
    }

    @Override
    public boolean setGameRuleValue(String rule, String value) {
        return false;
    }

    @Override
    public boolean isGameRule(String rule) {
        return false;
    }

    @Override
    public <T> T getGameRuleValue(GameRule<T> rule) {
        return null;
    }

    @Override
    public <T> T getGameRuleDefault(GameRule<T> rule) {
        return null;
    }

    @Override
    public <T> boolean setGameRule(GameRule<T> rule, T newValue) {
        return false;
    }

    // ===== Difficulty =====

    @Override
    public Difficulty getDifficulty() {
        net.minecraft.world.Difficulty nms = handle.getDifficulty();
        return Difficulty.getByValue(nms.getId());
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        net.minecraft.world.Difficulty nms = net.minecraft.world.Difficulty.byId(difficulty.getValue());
        handle.getServer().setDifficulty(nms, false);
    }

    // ===== World border =====

    @Override
    public WorldBorder getWorldBorder() {
        return null;
    }

    // ===== Save =====

    @Override
    public void save() {
        handle.save(null, true, false);
    }

    // ===== Spawn location =====

    @Override
    public Location getSpawnLocation() {
        BlockPos pos = handle.getSharedSpawnPos();
        return new Location(this, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean setSpawnLocation(Location location) {
        return setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getYaw());
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z, float angle) {
        handle.setDefaultSpawnPos(new BlockPos(x, y, z), angle);
        return true;
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z) {
        return setSpawnLocation(x, y, z, 0.0f);
    }

    // ===== PVP =====

    @Override
    public boolean getPVP() {
        return true;
    }

    @Override
    public void setPVP(boolean pvp) {
    }

    // ===== Generator =====

    @Override
    public ChunkGenerator getGenerator() {
        return null;
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return null;
    }

    @Override
    public List<BlockPopulator> getPopulators() {
        return Collections.emptyList();
    }

    // ===== Effects =====

    @Override
    public void playEffect(Location location, Effect effect, int data) {
    }

    @Override
    public void playEffect(Location location, Effect effect, int data, int radius) {
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T data) {
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T data, int radius) {
    }

    // ===== Snapshot =====

    @Override
    public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTempRain) {
        return null;
    }

    @Override
    public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals) {
    }

    @Override
    public boolean getAllowAnimals() {
        return true;
    }

    @Override
    public boolean getAllowMonsters() {
        return true;
    }

    // ===== Biome =====

    @Override
    public Biome getBiome(int x, int z) {
        return Biome.PLAINS;
    }

    @Override
    public Biome getBiome(int x, int y, int z) {
        return Biome.PLAINS;
    }

    @Override
    public Biome getBiome(Location location) {
        return getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public void setBiome(int x, int y, int z, Biome biome) {
    }

    @Override
    public void setBiome(Location location, Biome biome) {
    }

    @Override
    public void setBiome(int x, int z, Biome biome) {
    }

    @Override
    public double getTemperature(int x, int z) {
        return 0;
    }

    @Override
    public double getTemperature(int x, int y, int z) {
        return 0;
    }

    @Override
    public double getHumidity(int x, int z) {
        return 0;
    }

    @Override
    public double getHumidity(int x, int y, int z) {
        return 0;
    }

    // ===== World properties =====

    @Override
    public boolean isNatural() {
        return true;
    }

    @Override
    public boolean isBedWorks() {
        return true;
    }

    @Override
    public boolean hasSkyLight() {
        return handle.dimensionType().hasSkyLight();
    }

    @Override
    public boolean hasCeiling() {
        return false;
    }

    @Override
    public boolean isPiglinSafe() {
        return false;
    }

    @Override
    public boolean isRespawnAnchorWorks() {
        return true;
    }

    @Override
    public boolean hasRaids() {
        return true;
    }

    @Override
    public boolean isUltraWarm() {
        return handle.dimensionType().ultraWarm();
    }

    @Override
    public int getSeaLevel() {
        return handle.getSeaLevel();
    }

    @Override
    public boolean getKeepSpawnInMemory() {
        return true;
    }

    @Override
    public void setKeepSpawnInMemory(boolean keep) {
    }

    @Override
    public boolean isAutoSave() {
        return true;
    }

    @Override
    public void setAutoSave(boolean autoSave) {
    }

    @Override
    public File getWorldFolder() {
        return new File(".");
    }

    @Override
    public org.bukkit.WorldType getWorldType() {
        return org.bukkit.WorldType.NORMAL;
    }

    @Override
    public boolean canGenerateStructures() {
        return true;
    }

    @Override
    public boolean isHardcore() {
        return handle.getServer().isHardcore();
    }

    @Override
    public void setHardcore(boolean hardcore) {
    }

    @Override
    public Set<org.bukkit.FeatureFlag> getFeatureFlags() {
        return Collections.emptySet();
    }

    // ===== Spawn limits =====

    @Override
    public long getTicksPerAnimalSpawns() {
        return 400;
    }

    @Override
    public void setTicksPerAnimalSpawns(int ticks) {
    }

    @Override
    public long getTicksPerMonsterSpawns() {
        return 400;
    }

    @Override
    public void setTicksPerMonsterSpawns(int ticks) {
    }

    @Override
    public long getTicksPerWaterSpawns() {
        return 1;
    }

    @Override
    public void setTicksPerWaterSpawns(int ticks) {
    }

    @Override
    public long getTicksPerWaterAmbientSpawns() {
        return 1;
    }

    @Override
    public void setTicksPerWaterAmbientSpawns(int ticks) {
    }

    @Override
    public long getTicksPerWaterUndergroundCreatureSpawns() {
        return 1;
    }

    @Override
    public void setTicksPerWaterUndergroundCreatureSpawns(int ticks) {
    }

    @Override
    public long getTicksPerAmbientSpawns() {
        return 1;
    }

    @Override
    public void setTicksPerAmbientSpawns(int ticks) {
    }

    @Override
    public long getTicksPerSpawns(SpawnCategory category) {
        return 1;
    }

    @Override
    public void setTicksPerSpawns(SpawnCategory category, int ticks) {
    }

    @Override
    public int getMonsterSpawnLimit() {
        return 70;
    }

    @Override
    public void setMonsterSpawnLimit(int limit) {
    }

    @Override
    public int getAnimalSpawnLimit() {
        return 10;
    }

    @Override
    public void setAnimalSpawnLimit(int limit) {
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return 15;
    }

    @Override
    public void setWaterAnimalSpawnLimit(int limit) {
    }

    @Override
    public int getWaterUndergroundCreatureSpawnLimit() {
        return 5;
    }

    @Override
    public void setWaterUndergroundCreatureSpawnLimit(int limit) {
    }

    @Override
    public int getWaterAmbientSpawnLimit() {
        return 20;
    }

    @Override
    public void setWaterAmbientSpawnLimit(int limit) {
    }

    @Override
    public int getAmbientSpawnLimit() {
        return 15;
    }

    @Override
    public void setAmbientSpawnLimit(int limit) {
    }

    @Override
    public int getSpawnLimit(SpawnCategory category) {
        return 0;
    }

    @Override
    public void setSpawnLimit(SpawnCategory category, int limit) {
    }

    // ===== Sound =====

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
    }

    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {
    }

    @Override
    public void playSound(Entity entity, Sound sound, float volume, float pitch) {
    }

    @Override
    public void playSound(Entity entity, String sound, float volume, float pitch) {
    }

    @Override
    public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch) {
    }

    @Override
    public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch) {
    }

    // ===== Particles =====

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
    }

    // ===== Structure =====

    @Override
    public Location locateNearestStructure(Location origin, org.bukkit.StructureType structureType, int radius, boolean findUnexplored) {
        return null;
    }

    @Override
    public StructureSearchResult locateNearestStructure(Location origin, org.bukkit.generator.structure.StructureType structureType, int radius, boolean findUnexplored) {
        return null;
    }

    @Override
    public StructureSearchResult locateNearestStructure(Location origin, Structure structure, int radius, boolean findUnexplored) {
        return null;
    }

    @Override
    public Raid locateNearestRaid(Location location, int radius) {
        return null;
    }

    @Override
    public List<Raid> getRaids() {
        return Collections.emptyList();
    }

    // ===== Misc =====

    @Override
    public int getViewDistance() {
        return 8;
    }

    @Override
    public int getSimulationDistance() {
        return 8;
    }

    @Override
    public DragonBattle getEnderDragonBattle() {
        return null;
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return Collections.emptySet();
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue metadataValue) {
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return Collections.emptyList();
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin plugin) {
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public BlockState getBlockState(Location location) {
        return getBlockAt(location).getState();
    }

    @Override
    public BlockState getBlockState(int x, int y, int z) {
        return getBlockAt(x, y, z).getState();
    }

    @Override
    public BlockData getBlockData(Location location) {
        return getBlockAt(location).getBlockData();
    }

    @Override
    public BlockData getBlockData(int x, int y, int z) {
        return getBlockAt(x, y, z).getBlockData();
    }

    @Override
    public Material getType(Location location) {
        return getBlockAt(location).getType();
    }

    @Override
    public Material getType(int x, int y, int z) {
        return getBlockAt(x, y, z).getType();
    }

    @Override
    public void setBlockData(Location location, BlockData blockData) {
        getBlockAt(location).setBlockData(blockData);
    }

    @Override
    public void setBlockData(int x, int y, int z, BlockData blockData) {
        getBlockAt(x, y, z).setBlockData(blockData);
    }

    @Override
    public void setType(Location location, Material material) {
        getBlockAt(location).setType(material);
    }

    @Override
    public void setType(int x, int y, int z, Material material) {
        getBlockAt(x, y, z).setType(material);
    }

    @Override
    public Spigot spigot() {
        return null;
    }
}
