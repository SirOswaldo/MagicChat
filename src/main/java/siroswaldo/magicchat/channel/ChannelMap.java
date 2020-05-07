package siroswaldo.magicchat.channel;

import org.bukkit.configuration.file.FileConfiguration;
import siroswaldo.magicchat.bukkit.MagicChat;

import java.util.*;

public class ChannelMap {

    private final MagicChat magicChat;

    private Map<String, Channel> channels;

    public ChannelMap(MagicChat magicChat){
        this.magicChat = magicChat;
        channels = new HashMap<>();
    }

    public void loadChannels(){
        FileConfiguration settings = magicChat.getYamlFiles().getFileConfiguration("settings");
        Set<String> names = settings.getConfigurationSection("channels").getValues(false).keySet();
        for (String name:names){
            boolean enable = true;
            boolean Default = false;
            String format = "";
            String color = "&f";
            String permission = "none";
            boolean autoJoin = false;
            String command = "";
            boolean bungee = false;

            if (settings.contains("channels."+name+".default")){
                Default = settings.getBoolean("channels."+name+".default");
            }
            // Format
            if (settings.contains("channels."+name+".format")){
                format = settings.getString("channels."+name+".format");
            } else {
                enable = false;
            }
            // Color
            if (settings.contains("channels."+name+".color")){
                color = settings.getString("channels."+name+".color");
            }
            // Permission
            if (settings.contains("channels."+name+".permission")){
                permission = settings.getString("channels."+name+".permission");
            }
            // AutoJoin
            if (settings.contains("channels."+name+".autoJoin")){
                autoJoin = settings.getBoolean("channels."+name+".autoJoin");
            }
            // Command
            if (settings.contains("channels."+name+".command")){
                autoJoin = settings.getBoolean("channels."+name+".command");
            } else {
                enable = false;
            }
            // Bungee
            if (settings.contains("channels."+name+".bungee")){
                bungee = settings.getBoolean("channels."+name+".bungee");
            }


            if (enable){
                Channel channel = new Channel(name, Default, format, color, permission, autoJoin, command, bungee);
                channels.put(name, channel);
            }
        }
    }

    public void reloadChannels(){
        channels = new HashMap<>();
        loadChannels();
    }

    public boolean containChannel(String channel){
        return channels.containsKey(channel);
    }

    public void addChannel(Channel channel){
        channels.put(channel.getName(), channel);
    }

    public void removeChannel(String channel){
        channels.remove(channel);
    }

    public Channel getChannel(String channel){
        return channels.get(channel);
    }

    public List<String> getChannels(){
        return new ArrayList<>(channels.keySet());
    }
}
