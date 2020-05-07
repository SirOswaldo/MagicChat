package siroswaldo.magicchat.playerdata;

import org.bukkit.entity.Player;
import siroswaldo.magicchat.bukkit.MagicChat;
import siroswaldo.magicchat.channel.Channel;
import siroswaldo.magicchat.channel.ChannelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerDataMap {

    private final MagicChat magicChat;
    private Map<UUID, PlayerData> playerDataMap;

    public PlayerDataMap(MagicChat magicChat){
        this.magicChat = magicChat;
        playerDataMap = new HashMap<>();
    }

    public void loadPlayerData(){
        ChannelMap channelMap = magicChat.getChannelsMap();
        List<String> names = channelMap.getChannels();
        for(Player player: magicChat.getServer().getOnlinePlayers()){
            UUID uuid = player.getUniqueId();
            PlayerData playerData = new PlayerData(uuid);
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
            addPlayerData(playerData);
        }
    }

    public void reloadPlayerData(){
        playerDataMap = new HashMap<>();
        loadPlayerData();
    }

    public boolean containPlayerData(UUID uuid){
        return playerDataMap.containsKey(uuid);
    }

    public void addPlayerData(PlayerData playerData){
        playerDataMap.put(playerData.getUuid(), playerData);
    }

    public void removePlayerData(UUID uuid){
        playerDataMap.remove(uuid);
    }

    public PlayerData getPlayerData(UUID uuid){
        return playerDataMap.get(uuid);
    }
}
