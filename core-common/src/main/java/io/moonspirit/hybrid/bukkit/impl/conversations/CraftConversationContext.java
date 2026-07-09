package io.moonspirit.hybrid.bukkit.impl.conversations;

import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class CraftConversationContext extends ConversationContext {

    public CraftConversationContext(Plugin plugin, Conversable forWhom, Map<Object, Object> initialSessionData) {
        super(plugin, forWhom, initialSessionData);
    }
}
