package io.moonspirit.hybrid.bukkit.impl;

import io.moonspirit.hybrid.bukkit.impl.block.CraftBlockState;
import io.moonspirit.hybrid.bukkit.impl.block.CraftChest;
import io.moonspirit.hybrid.bukkit.impl.block.CraftFurnace;
import io.moonspirit.hybrid.bukkit.impl.block.CraftSign;
import io.moonspirit.hybrid.mod.server.BukkitRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.bukkit.Chunk;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.bukkit.util.VoxelShape;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CraftBlock implements Block {

    private final CraftWorld craftWorld;
    private final BlockPos position;

    public CraftBlock(CraftWorld world, BlockPos pos) {
        this.craftWorld = world;
        this.position = pos;
    }

    public ServerLevel getHandle() {
        return craftWorld.getHandle();
    }

    public BlockPos getPosition() {
        return position;
    }

    public static Material toBukkitMaterial(net.minecraft.world.level.block.Block block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        if (key == null) return Material.AIR;
        String name = key.getPath().toUpperCase(Locale.ROOT);
        Material mat = Material.getMaterial(name);
        return mat != null ? mat : Material.AIR;
    }

    public static net.minecraft.world.level.block.Block fromBukkitMaterial(Material material) {
        return BukkitRegistry.MATERIAL_TO_BLOCK.getOrDefault(material, net.minecraft.world.level.block.Blocks.AIR);
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public int getZ() {
        return position.getZ();
    }

    @Override
    public World getWorld() {
        return craftWorld;
    }

    @Override
    public Location getLocation() {
        return new Location(craftWorld, position.getX(), position.getY(), position.getZ());
    }

    @Override
    public Location getLocation(Location loc) {
        loc.setWorld(craftWorld);
        loc.setX(position.getX());
        loc.setY(position.getY());
        loc.setZ(position.getZ());
        return loc;
    }

    @Override
    public Chunk getChunk() {
        return craftWorld.getChunkAt(position.getX() >> 4, position.getZ() >> 4);
    }

    @Override
    public Material getType() {
        return toBukkitMaterial(getHandle().getBlockState(position).getBlock());
    }

    @Override
    public void setType(Material type) {
        setType(type, true);
    }

    @Override
    public void setType(Material type, boolean applyPhysics) {
        net.minecraft.world.level.block.Block nmsBlock = fromBukkitMaterial(type);
        int flags = applyPhysics ? 3 : 2;
        getHandle().setBlock(position, nmsBlock.defaultBlockState(), flags);
    }

    @Override
    public byte getData() {
        return 0;
    }

    @Override
    public BlockData getBlockData() {
        return null;
    }

    @Override
    public void setBlockData(BlockData data) {
    }

    @Override
    public void setBlockData(BlockData data, boolean applyPhysics) {
    }

    @Override
    public BlockState getState() {
        BlockEntity be = getHandle().getBlockEntity(position);
        if (be instanceof ChestBlockEntity) {
            return new CraftChest(craftWorld, position);
        } else if (be instanceof FurnaceBlockEntity) {
            return new CraftFurnace(craftWorld, position);
        } else if (be instanceof SignBlockEntity) {
            return new CraftSign(craftWorld, position);
        }
        return new CraftBlockState(craftWorld, position);
    }

    @Override
    public org.bukkit.block.Biome getBiome() {
        return org.bukkit.block.Biome.PLAINS;
    }

    @Override
    public void setBiome(org.bukkit.block.Biome biome) {
    }

    @Override
    public boolean isBlockPowered() {
        return false;
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return false;
    }

    @Override
    public boolean isBlockFacePowered(BlockFace face) {
        return false;
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        return false;
    }

    @Override
    public int getBlockPower(BlockFace face) {
        return 0;
    }

    @Override
    public int getBlockPower() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return getType() == Material.AIR;
    }

    @Override
    public boolean isLiquid() {
        return false;
    }

    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        return new CraftBlock(craftWorld, position.offset(modX, modY, modZ));
    }

    @Override
    public Block getRelative(BlockFace face) {
        return getRelative(face, 1);
    }

    @Override
    public Block getRelative(BlockFace face, int distance) {
        Direction dir = Direction.valueOf(face.name());
        return new CraftBlock(craftWorld, position.relative(dir, distance));
    }

    @Override
    public BlockFace getFace(Block block) {
        return BlockFace.SELF;
    }

    @Override
    public byte getLightLevel() {
        return 0;
    }

    @Override
    public byte getLightFromSky() {
        return 0;
    }

    @Override
    public byte getLightFromBlocks() {
        return 0;
    }

    @Override
    public double getTemperature() {
        return 0;
    }

    @Override
    public double getHumidity() {
        return 0;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return PistonMoveReaction.MOVE;
    }

    @Override
    public boolean breakNaturally() {
        getHandle().destroyBlock(position, true);
        return true;
    }

    @Override
    public boolean breakNaturally(ItemStack tool) {
        return breakNaturally();
    }

    @Override
    public boolean applyBoneMeal(BlockFace face) {
        return false;
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool) {
        return Collections.emptyList();
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool, Entity entity) {
        return Collections.emptyList();
    }

    @Override
    public boolean isPreferredTool(ItemStack tool) {
        return false;
    }

    @Override
    public float getBreakSpeed(Player player) {
        return 0;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public RayTraceResult rayTrace(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode) {
        return null;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }

    @Override
    public VoxelShape getCollisionShape() {
        return null;
    }

    @Override
    public boolean canPlace(BlockData data) {
        return false;
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
    public String getTranslationKey() {
        return getHandle().getBlockState(position).getBlock().getDescriptionId();
    }
}
