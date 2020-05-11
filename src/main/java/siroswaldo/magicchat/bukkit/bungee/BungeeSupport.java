package siroswaldo.magicchat.bukkit.bungee;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeSupport {

    private final JavaPlugin javaPlugin;
    private final boolean enable;

    public BungeeSupport(JavaPlugin javaPlugin){
        this.javaPlugin = javaPlugin;
        if ( !javaPlugin.getServer().getVersion().contains( "Spigot" ) && !javaPlugin.getServer().getVersion().contains( "Paper" ) ){
            enable = javaPlugin.getServer().spigot().getConfig().getBoolean("settings.bungeecord");
        }else{
            enable = false;
        }
    }

    public boolean registerChannel(String channel){
        if (enable){
            javaPlugin.getServer().getMessenger().registerOutgoingPluginChannel(javaPlugin, channel);
            return true;
        }
        return false;
    }

    public boolean registerMessageListener(PluginMessageListener listener, String channel){
        if (enable){
            javaPlugin.getServer().getMessenger().registerIncomingPluginChannel(javaPlugin, channel, listener);
            return true;
        } else {
            return false;
        }
    }


}
