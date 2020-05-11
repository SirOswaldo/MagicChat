package siroswaldo.magicchat.bungee;

import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Eventos implements Listener {

    private final MagicChat magicChat;

    public Eventos(MagicChat magicChat){
        this.magicChat = magicChat;
    }

    @EventHandler
    public void onChat(ChatEvent event){
        magicChat.getLogger().info("=========================== ChatEvent");
        magicChat.getLogger().info("");
        magicChat.getLogger().info("Sender: " +event.getSender());
        magicChat.getLogger().info("Receiver: " +event.getReceiver());
        magicChat.getLogger().info("Message: " +event.getMessage());
        magicChat.getLogger().info("");
        magicChat.getLogger().info("");
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event){
        magicChat.getLogger().info("=========================== PluginMessageEvent");
        magicChat.getLogger().info("");
        magicChat.getLogger().info("Sender: " + event.getSender());
        magicChat.getLogger().info("Receiver: " + event.getReceiver());
        magicChat.getLogger().info("Tag:" + event.getTag());
        magicChat.getLogger().info("Data: " +event.getData());
        magicChat.getLogger().info("");
        magicChat.getLogger().info("");
    }
}
