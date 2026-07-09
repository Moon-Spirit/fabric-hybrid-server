package io.moonspirit.hybrid.bukkit.impl.conversations;

import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.Prompt;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class CraftConversation extends org.bukkit.conversations.Conversation {

    public CraftConversation(Plugin plugin, Conversable forWhom, Prompt firstPrompt) {
        super(plugin, forWhom, firstPrompt);
    }

    public CraftConversation(Plugin plugin, Conversable forWhom, Prompt firstPrompt,
                             Map<Object, Object> initialSessionData) {
        super(plugin, forWhom, firstPrompt, initialSessionData);
    }

    @Override
    public void begin() {
    }

    @Override
    public void acceptInput(String input) {
    }

    @Override
    public boolean isModal() {
        return false;
    }

    @Override
    public void setLocalEchoEnabled(boolean localEchoEnabled) {
    }

    @Override
    public void addConversationAbandonedListener(ConversationAbandonedListener listener) {
    }

    @Override
    public void removeConversationAbandonedListener(ConversationAbandonedListener listener) {
    }

    @Override
    public void abandon() {
    }

    @Override
    public void abandon(ConversationAbandonedEvent event) {
    }

    @Override
    public void outputNextPrompt() {
    }
}
