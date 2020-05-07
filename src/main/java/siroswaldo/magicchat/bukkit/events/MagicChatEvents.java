package siroswaldo.magicchat.bukkit.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import siroswaldo.magicchat.bukkit.MagicChat;
import siroswaldo.magicchat.channel.Channel;
import siroswaldo.magicchat.channel.ChannelMap;
import siroswaldo.magicchat.playerdata.PlayerData;
import siroswaldo.magicchat.util.message.StringMessage;

import java.io.ByteArrayInputStream;
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
        String command = event.getMessage().replaceFirst("/", "");
        if (magicChat.getPlayerDataMap().containPlayerData(uuid)){
            List<String> names = magicChat.getChannelsMap().getChannels();
            for (String name:names){
                Channel channel = magicChat.getChannelsMap().getChannel(name);
                if (command.equalsIgnoreCase(channel.getCommand())){
                    FileConfiguration messages = magicChat.getYamlFiles().getFileConfiguration("messages");
                    String prefix = messages.getString("prefix");
                    PlayerData playerData = magicChat.getPlayerDataMap().getPlayerData(uuid);
                    if (playerData.containChannel(name)){
                        if (playerData.getCurrentChannel().equals(name)){
                            StringMessage channelAlreadySelected = new StringMessage(prefix + messages.getString("channelAlreadySelected"));
                            channelAlreadySelected.replaceAll("%channel%", name);
                            channelAlreadySelected.addColor();
                            channelAlreadySelected.sendMessage(player);
                        } else{
                            playerData.setCurrentChannel(name);
                            StringMessage channelSelected = new StringMessage(prefix +messages.getString("channelSelected"));
                            channelSelected.replaceAll("%channel%", name);
                            channelSelected.addColor();
                            channelSelected.sendMessage(player);
                        }
                        return;
                    } else {
                        if (player.hasPermission(channel.getPermission())){
                            playerData.addChannel(name);
                            playerData.setCurrentChannel(name);
                            channel.addPlayer(player);
                            StringMessage channelSelected = new StringMessage(prefix +messages.getString("channelSelected"));
                            channelSelected.replaceAll("%channel%", name);
                            channelSelected.addColor();
                            channelSelected.sendMessage(player);
                        } else {
                            StringMessage noPermissionToSelectChannel = new StringMessage(prefix +messages.getString("noPermissionToSelectChannel"));
                            noPermissionToSelectChannel.replaceAll("%channel%", name);
                            noPermissionToSelectChannel.addColor();
                            noPermissionToSelectChannel.sendMessage(player);
                        }
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Player eventPlayer = event.getPlayer();
        UUID uuid = eventPlayer.getUniqueId();
        if (magicChat.getPlayerDataMap().containPlayerData(uuid)){
            PlayerData eventPlayerData = magicChat.getPlayerDataMap().getPlayerData(uuid);
            Channel channel = magicChat.getChannelsMap().getChannel(eventPlayerData.getCurrentChannel());
            StringMessage text = new StringMessage(event.getMessage());
            if (eventPlayer.hasPermission("magicchat.color")){
                text.addColor();
            }
            StringMessage chat = new StringMessage(channel.getFormat());
            chat.addPlaceHolders(eventPlayer);
            chat.appendText(channel.getColor());
            chat.addColor();
            chat.appendText(text.getMessage());
            if (channel.isBungee()){
                ByteArrayDataOutput output = ByteStreams.newDataOutput();
                output.writeUTF("message:send");
                output.writeUTF(chat.getMessage());
                eventPlayer.sendPluginMessage(magicChat,"magic:chat", output.toByteArray());
            } else {
                for(Player player:magicChat.getServer().getOnlinePlayers()){
                    if (magicChat.getPlayerDataMap().containPlayerData(player.getUniqueId())){
                        PlayerData playerData = magicChat.getPlayerDataMap().getPlayerData(player.getUniqueId());
                        if (playerData.containChannel(channel.getName())){
                            player.sendMessage(chat.getMessage());
                        }
                    }
                }
            }
        }
    }

}
