package siroswaldo.magicchat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import siroswaldo.magicchat.MagicChat;

public class MagicChatEvents implements Listener {

    private final MagicChat magicChat;

    public MagicChatEvents(MagicChat magicChat){
        this.magicChat = magicChat;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){

    }

}
