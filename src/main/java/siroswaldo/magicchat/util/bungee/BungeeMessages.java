package siroswaldo.magicchat.util.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import siroswaldo.magicchat.MagicChat;
import siroswaldo.magicchat.channel.Channel;

import static com.google.common.io.ByteStreams.newDataInput;

public class BungeeMessages implements PluginMessageListener {

    private final MagicChat magicChat;
    private final String bungeeChannel;

    public BungeeMessages(MagicChat magicChat, String bungeeChannel){
        this.magicChat = magicChat;
        this.bungeeChannel = bungeeChannel;
    }

    public void registerBungeeMessaging(){
        magicChat.getServer().getMessenger().registerIncomingPluginChannel(magicChat, bungeeChannel, this);
        magicChat.getServer().getMessenger().registerOutgoingPluginChannel(magicChat, bungeeChannel);
    }

    @Override
    public void onPluginMessageReceived(String senderChannel, Player player, byte[] message) {
        if (senderChannel.equals(bungeeChannel)) {
            ByteArrayDataInput in = newDataInput(message);
            String name = in.readUTF();
            if (magicChat.getChannelsMap().containChannel(name)){
                Channel channel = magicChat.getChannelsMap().getChannel(name);
                if(channel.isBungee()){
                    channel.sendMessage(player, in.readUTF());
                }
            }
        }
    }
}
