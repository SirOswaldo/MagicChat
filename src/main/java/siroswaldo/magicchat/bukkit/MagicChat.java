package siroswaldo.magicchat.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.magicchat.channel.ChannelMap;
import siroswaldo.magicchat.bukkit.commands.MagicChatCommand;
import siroswaldo.magicchat.bukkit.commands.TestCommand;
import siroswaldo.magicchat.bukkit.events.MagicChatEvents;
import siroswaldo.magicchat.playerdata.PlayerDataMap;
import siroswaldo.magicchat.bukkit.listener.BungeeMessages;
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
        bungeeMessages = new BungeeMessages(this);
        bungeeMessages.registerBungeeMessaging();
        registerCommands();
        registerEvents();
        enableAndDisable.sendEnable();
        Pruebas pruebas = new Pruebas(this);
        pruebas.startScheduler();
    }

    @Override
    public void onDisable() {
        enableAndDisable.sendDisable();
    }

    public BungeeMessages getBungeeMessages() {
        return bungeeMessages;
    }

    private void registerFiles(){
        yamlFiles = new YamlFiles(this);
        yamlFiles.addFile("settings");
        yamlFiles.addFile("messages");
        yamlFiles.registerFiles();
    }

    private void registerCommands(){
        getCommand("magicchat").setExecutor(new MagicChatCommand(this));
        getCommand("test").setExecutor(new TestCommand(this));
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
