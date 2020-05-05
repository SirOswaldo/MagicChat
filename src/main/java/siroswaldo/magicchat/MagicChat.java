package siroswaldo.magicchat;

import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.magicchat.channel.ChannelMap;
import siroswaldo.magicchat.commands.MagicChatCommand;
import siroswaldo.magicchat.events.MagicChatEvents;
import siroswaldo.magicchat.playerdata.PlayerDataMap;
import siroswaldo.magicchat.util.bungee.BungeeMessages;
import siroswaldo.magicchat.util.message.EnableAndDisable;
import siroswaldo.magicchat.util.yaml.YamlFiles;

public class MagicChat extends JavaPlugin {

    private EnableAndDisable enableAndDisable;
    private BungeeMessages bungeeMessages;


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
        registerFiles();
        channelMap = new ChannelMap(this);
        channelMap.loadChannels();
        playerDataMap = new PlayerDataMap(this);
        playerDataMap.loadPlayerData();
        bungeeMessages = new BungeeMessages(this, "MagicChat");
        bungeeMessages.registerBungeeMessaging();
        registerCommands();
        registerEvents();
        enableAndDisable.sendEnable();
    }

    @Override
    public void onDisable() {
        enableAndDisable.sendDisable();
    }

    private void registerFiles(){
        yamlFiles = new YamlFiles(this);
        yamlFiles.addFile("settings");
        yamlFiles.addFile("messages");
        yamlFiles.registerFiles();
    }

    private void registerCommands(){
        getCommand("magicchat").setExecutor(new MagicChatCommand(this));
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new MagicChatEvents(this), this);
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
