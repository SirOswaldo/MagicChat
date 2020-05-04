package siroswaldo.magicchat.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import siroswaldo.magicchat.MagicChat;
import siroswaldo.magicchat.channel.Channel;
import siroswaldo.magicchat.channel.ChannelMap;
import siroswaldo.magicchat.playerdata.PlayerData;
import siroswaldo.magicchat.util.message.StringMessage;

import java.util.List;
import java.util.UUID;

public class MagicChatEvents implements Listener {

    private final MagicChat magicChat;

    public MagicChatEvents(MagicChat magicChat){
        this.magicChat = magicChat;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerData playerData = new PlayerData(uuid);
        ChannelMap channelMap = magicChat.getChannelsMap();
        List<String> names = channelMap.getChannels();
        for (String name:names){
            Channel channel = magicChat.getChannelsMap().getChannel(name);
            if (channel.getPermission().equals("none") || player.hasPermission(channel.getPermission())){
                playerData.addChannel(name);
                if (channel.isDefault()){
                    playerData.setCurrentChannel(name);
                }
                channel.addPlayer(player);
            }
        }
        magicChat.getPlayerDataMap().addPlayerData(playerData);
        // Login Message
        FileConfiguration settings = magicChat.getYamlFiles().getFileConfiguration("settings");
        if (settings.getBoolean("joinMessage.enable")) {
            StringMessage joinMessage = new StringMessage(settings.getString("joinMessage.message"));
            joinMessage.addPlaceHolders(player);
            joinMessage.addColor();
            event.setJoinMessage(joinMessage.toString());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (magicChat.getPlayerDataMap().containPlayerData(uuid)){
            PlayerData playerData = magicChat.getPlayerDataMap().getPlayerData(uuid);
            List<String> names = playerData.getChannels();
            for (String name:names){
                Channel channel = magicChat.getChannelsMap().getChannel(name);
                channel.removePlayer(player);
            }
            magicChat.getPlayerDataMap().removePlayerData(uuid);
        }
        // Quit Message
        FileConfiguration settings = magicChat.getYamlFiles().getFileConfiguration("settings");
        if (settings.getBoolean("quitMessage.enable")){
            StringMessage quitMessage = new StringMessage(settings.getString("quitMessage.message"));
            quitMessage.addPlaceHolders(player);
            quitMessage.addColor();
            event.setQuitMessage(quitMessage.toString());
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        String command;
        if (event.getMessage().contains(" ")) command = event.getMessage().split(" ")[0];
        else {
            command = event.getMessage();
        }
        command = command.replaceFirst("/", "");
        if (magicChat.getPlayerDataMap().containPlayerData(uuid)){
            PlayerData playerData = magicChat.getPlayerDataMap().getPlayerData(uuid);
            List<String> names = playerData.getChannels();
            for (String name:names){
                Channel channel = magicChat.getChannelsMap().getChannel(name);
                if(channel.getAliases().contains(command)){
                    event.setCancelled(true);
                    FileConfiguration messages = magicChat.getYamlFiles().getFileConfiguration("messages");
                    if (playerData.getCurrentChannel().equals(name)){
                        StringMessage channelAlreadySelected = new StringMessage(messages.getString("channelAlreadySelected"));
                        channelAlreadySelected.addPlaceHolders(player);
                        channelAlreadySelected.addColor();
                        channelAlreadySelected.sendMessage(player);
                    } else {
                        playerData.setCurrentChannel(name);
                        StringMessage channelSelected = new StringMessage(messages.getString("channelSelected"));
                        channelSelected.addPlaceHolders(player);
                        channelSelected.addColor();
                        channelSelected.sendMessage(player);
                    }
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (magicChat.getPlayerDataMap().containPlayerData(uuid)){
            PlayerData playerData = magicChat.getPlayerDataMap().getPlayerData(uuid);
            Channel channel = magicChat.getChannelsMap().getChannel(playerData.getCurrentChannel());
            StringMessage message = new StringMessage(event.getMessage());
            if (player.hasPermission("magicchat.color")){
                message.addColor();
            }
            channel.sendMessage(player, message.getMessage());

        }
    }

}
