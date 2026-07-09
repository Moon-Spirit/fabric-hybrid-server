package io.moonspirit.hybrid.bukkit.impl.command;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;

import java.util.Set;
import java.util.UUID;

public class CraftConsoleCommandSender implements ConsoleCommandSender {

    private final PermissibleBase perm = new PermissibleBase(this);

    @Override
    public void sendRawMessage(String message) {
    }

    @Override
    public void sendMessage(String message) {}

    @Override
    public void sendMessage(String... messages) {}

    @Override
    public void sendMessage(UUID sender, String message) {}

    @Override
    public void sendMessage(UUID sender, String... messages) {}

    @Override
    public Server getServer() { return null; }

    @Override
    public String getName() { return "CONSOLE"; }

    @Override
    public boolean isPermissionSet(String name) { return perm.isPermissionSet(name); }

    @Override
    public boolean isPermissionSet(Permission perm) { return this.perm.isPermissionSet(perm); }

    @Override
    public boolean hasPermission(String name) { return true; }

    @Override
    public boolean hasPermission(Permission perm) { return true; }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) { return perm.addAttachment(plugin, name, value); }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) { return perm.addAttachment(plugin); }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) { return perm.addAttachment(plugin, name, value, ticks); }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) { return perm.addAttachment(plugin, ticks); }

    @Override
    public void removeAttachment(PermissionAttachment attachment) { perm.removeAttachment(attachment); }

    @Override
    public void recalculatePermissions() { perm.recalculatePermissions(); }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() { return perm.getEffectivePermissions(); }

    @Override
    public boolean isOp() { return true; }

    @Override
    public void setOp(boolean value) {}

    @Override
    public Spigot spigot() { return null; }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return false;
    }

    @Override
    public boolean isConversing() {
        return false;
    }

    @Override
    public void acceptConversationInput(String input) {
    }

    @Override
    public void abandonConversation(Conversation conversation) {
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
    }

    @Override
    public void sendRawMessage(UUID uuid, String message) {
        sendRawMessage(message);
    }
}
