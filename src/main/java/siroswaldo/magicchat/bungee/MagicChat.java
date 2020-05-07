package siroswaldo.magicchat.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import siroswaldo.magicchat.bungee.listener.MessageListener;

import java.util.logging.Level;

public class MagicChat extends Plugin {

    @Override
    public void onEnable() {
        getProxy().registerChannel("magic:chat");
        getProxy().getPluginManager().registerListener(this, new MessageListener(this));
        getLogger().log(Level.INFO, "MagicChat>>> Canal de MagicChat fue registrado con exito");
        getLogger().log(Level.INFO, "MagicChat>>> Ya se estan escuchando los mensajes!");
        getProxy().getPluginManager().registerListener(this, new Eventos(this));
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "MagicChat>>> Adios :D");
    }
}
