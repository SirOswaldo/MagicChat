package siroswaldo.magicchat.playerdata;

import siroswaldo.magicchat.MagicChat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataMap {

    private final MagicChat magicChat;
    private Map<UUID, PlayerData> playerDataMap;

    public PlayerDataMap(MagicChat magicChat){
        this.magicChat = magicChat;
        playerDataMap = new HashMap<>();
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
