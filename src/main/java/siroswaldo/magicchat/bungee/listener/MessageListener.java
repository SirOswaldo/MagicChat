package siroswaldo.magicchat.bungee.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import siroswaldo.magicchat.bungee.MagicChat;

public class MessageListener implements Listener {

    private final MagicChat magicChat;

    public MessageListener(MagicChat magicChat){
        this.magicChat = magicChat;
    }

    @EventHandler
    public void onReceivedMessage(PluginMessageEvent event) {
        if (event.getTag().equals("magic:chat")){
            ByteArrayDataInput in = ByteStreams.newDataInput( event.getData() );
            String subChannel = in.readUTF();
            if ( subChannel.equals( "message:send" ) ) {
                String channel = in.readUTF();
                String message = in.readUTF();
                for(ProxiedPlayer proxiedPlayer:magicChat.getProxy().getPlayers()){
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("message:receive");
                    out.writeUTF(channel);
                    out.writeUTF(message);
                    proxiedPlayer.sendData("magic:chat", out.toByteArray());
                }
            }
            if (subChannel.equals("message:test")){
                magicChat.getLogger().info("Mensaje de prueba recibido");
                String message = in.readUTF();
                magicChat.getLogger().info("Mensaje: "+message);
                for (String name:magicChat.getProxy().getServers().keySet()){
                    ServerInfo serverInfo = magicChat.getProxy().getServerInfo(name);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("message:test");
                    out.writeUTF(message);
                    serverInfo.sendData("magic:chat", out.toByteArray());
                }
            }
        }
    }

}
