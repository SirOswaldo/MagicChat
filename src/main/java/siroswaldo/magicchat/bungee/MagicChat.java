package siroswaldo.magicchat.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import siroswaldo.magicchat.bungee.listener.MessageListener;
import siroswaldo.magicchat.bungee.util.YamlFile;

import java.util.logging.Level;

public class MagicChat extends Plugin {

    private YamlFile bungeeSettings;

    @Override
    public void onEnable() {
        getProxy().registerChannel("magic:chat");
        getProxy().getPluginManager().registerListener(this, new MessageListener(this));
        getLogger().log(Level.INFO, "MagicChat>>> Canal de MagicChat fue registrado con exito");
        getLogger().log(Level.INFO, "MagicChat>>> Ya se estan escuchando los mensajes!");
        getProxy().getPluginManager().registerListener(this, new Eventos(this));
        bungeeSettings = new YamlFile(this, "bungeeSettings");
        bungeeSettings.registerFile();
        Configuration settings = bungeeSettings.getFile();
        getLogger().log(Level.INFO, "leyendo: " +settings.getString("leyendo"));
        settings.set("escribiendo", "Funciona");
        bungeeSettings.saveFile();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "MagicChat>>> Adios :D");
    }
}
