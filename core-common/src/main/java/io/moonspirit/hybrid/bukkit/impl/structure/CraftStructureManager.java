package io.moonspirit.hybrid.bukkit.impl.structure;

import org.bukkit.NamespacedKey;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

public class CraftStructureManager implements StructureManager {

    @Override
    public Map<NamespacedKey, Structure> getStructures() {
        return Collections.emptyMap();
    }

    @Override
    public Structure getStructure(NamespacedKey key) {
        return null;
    }

    @Override
    public Structure registerStructure(NamespacedKey key, Structure structure) {
        return null;
    }

    @Override
    public Structure unregisterStructure(NamespacedKey key) {
        return null;
    }

    @Override
    public Structure loadStructure(NamespacedKey key, boolean register) {
        return null;
    }

    @Override
    public Structure loadStructure(NamespacedKey key) {
        return null;
    }

    @Override
    public void saveStructure(NamespacedKey key) {
    }

    @Override
    public void saveStructure(NamespacedKey key, Structure structure) throws IOException {
    }

    @Override
    public void deleteStructure(NamespacedKey key) throws IOException {
    }

    @Override
    public void deleteStructure(NamespacedKey key, boolean force) throws IOException {
    }

    @Override
    public File getStructureFile(NamespacedKey key) {
        return null;
    }

    @Override
    public Structure loadStructure(File file) throws IOException {
        return null;
    }

    @Override
    public Structure loadStructure(InputStream inputStream) throws IOException {
        return null;
    }

    @Override
    public void saveStructure(File file, Structure structure) throws IOException {
    }

    @Override
    public void saveStructure(OutputStream outputStream, Structure structure) throws IOException {
    }

    @Override
    public Structure createStructure() {
        return new CraftStructure();
    }

    @Override
    public Structure copy(Structure structure) {
        return new CraftStructure();
    }
}
