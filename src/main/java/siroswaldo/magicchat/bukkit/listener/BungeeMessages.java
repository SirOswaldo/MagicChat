package siroswaldo.magicchat.bukkit.listener;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import siroswaldo.magicchat.bukkit.MagicChat;
import siroswaldo.magicchat.channel.Channel;
import siroswaldo.magicchat.util.message.StringMessage;

import static com.google.common.io.ByteStreams.newDataInput;

public class BungeeMessages implements PluginMessageListener{

    private final MagicChat magicChat;

    public BungeeMessages(MagicChat magicChat){
        this.magicChat = magicChat;
    }

    public void registerBungeeMessaging(){
        magicChat.getServer().getMessenger().registerIncomingPluginChannel(magicChat, "magic:chat", this);
        magicChat.getServer().getMessenger().registerOutgoingPluginChannel(magicChat, "magic:chat");
    }

    @Override
    public void onPluginMessageReceived(String senderChannel, Player player, byte[] bytesMessage) {
        if (senderChannel.equals("magic:chat")) {
            ByteArrayDataInput in = newDataInput(bytesMessage);
            String subChannel = in.readUTF();
            if (subChannel.equals("message:receive")){
                String channelName = in.readUTF();
                String message = in.readUTF();
                if (magicChat.getChannelsMap().containChannel(channelName)){
                    Channel channel = magicChat.getChannelsMap().getChannel(channelName);
                    if(channel.isBungee()){
                        channel.sendMessage(magicChat, player, message);
                    }
                }
            }
            if (subChannel.equals("message:test")){
                String message = in.readUTF();
                StringMessage chat = new StringMessage(message);
                chat.announceMessage(magicChat.getServer());
                chat.logConsole(magicChat.getServer());
            }
        }
    }
}
