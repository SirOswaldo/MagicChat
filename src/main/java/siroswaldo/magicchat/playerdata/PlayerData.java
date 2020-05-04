package siroswaldo.magicchat.playerdata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData {

    private UUID uuid;
    private List<String> channels;
    private String currentChannel;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        channels = new ArrayList<>();
        currentChannel = "none";
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean containChannel(String channel){
        return channels.contains(channel);
    }

    public void addChannel(String channel){
        channels.add(channel);
    }

    public void removeChannel(String channel){
        while (containChannel(channel)){
            channels.remove(channel);
        }
    }

    public List<String> getChannels() {
        return new ArrayList<>(channels);
    }

    public String getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(String currentChannel) {
        this.currentChannel = currentChannel;
    }
}
