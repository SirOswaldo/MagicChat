package siroswaldo.magicchat;

import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.magicchat.channel.ChannelMap;
import siroswaldo.magicchat.playerdata.PlayerDataMap;
import siroswaldo.magicchat.util.message.EnableAndDisable;
import siroswaldo.magicchat.util.yaml.YamlFiles;

public class MagicChat extends JavaPlugin {

    private EnableAndDisable enableAndDisable;

    private YamlFiles yamlFiles;
    private ChannelMap channelMap;
    private PlayerDataMap playerDataMap;

    @Override
    public void onEnable() {
        enableAndDisable = new EnableAndDisable(this);
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null){
            enableAndDisable.sendDisable();
            getServer().getPluginManager().disablePlugin(this);
        }
        channelMap = new ChannelMap(this);
        channelMap.loadChannels();
        playerDataMap = new PlayerDataMap(this);

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public YamlFiles getYamlFiles() {
        return yamlFiles;
    }

    public ChannelMap getChannelsMap() {
        return channelMap;
    }

    public PlayerDataMap getPlayerDataMap() {
        return playerDataMap;
    }
}
