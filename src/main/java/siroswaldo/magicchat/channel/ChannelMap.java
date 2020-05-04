package siroswaldo.magicchat.channel;

import org.bukkit.configuration.file.FileConfiguration;
import siroswaldo.magicchat.MagicChat;

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
            String format = "";
            String color = "";
            String permission = "";
            boolean autoJoin = true;
            List<String> aliases = new ArrayList<>();
            // Format
            if (settings.contains("channels."+name+".format")){
                format = settings.getString("channels."+name+".format");
            } else {
                enable = false;
            }
            // Color
            if (settings.contains("channels."+name+".color")){
                color = settings.getString("channels."+name+".color");
            } else {
                enable = false;
            }
            // Permission
            if (settings.contains("channels."+name+".permission")){
                permission = settings.getString("channels."+name+".permission");
            } else {
                enable = false;
            }
            // AutoJoin
            if (settings.contains("channels."+name+".autoJoin")){
                autoJoin = settings.getBoolean("channels."+name+".autoJoin");
            } else {
                enable = false;
            }
            // Aliases
            if (settings.contains("channels."+name+".aliases")){
                autoJoin = settings.getBoolean("channels."+name+".aliases");
            } else {
                enable = false;
            }
            if (enable){
                Channel channel = new Channel(name,format, color, permission, autoJoin, aliases);
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
}
