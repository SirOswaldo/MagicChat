package siroswaldo.magicchat.bungee.util;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class YamlFile {

    private final Plugin plugin;
    private final String name;
    private File file;
    private Configuration configuration;

    public YamlFile(Plugin plugin, String name){
        this.plugin = plugin;
        this.name = name + ".yml";
        file = new File(plugin.getDataFolder(), this.name);
    }

    public void registerFile(){
        file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            reloadFile();
            saveFile();
        }
    }

    public void reloadFile() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        try{
            file = new File(plugin.getDataFolder(), name);
            if(!file.exists()) {
                file.createNewFile();
                Reader defConfigStream = new InputStreamReader(plugin.getResourceAsStream(name), StandardCharsets.UTF_8);
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(defConfigStream);
            } else{
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(plugin.getDataFolder(), name));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Configuration getFile(){
        if (configuration == null){
            reloadFile();
        }
        return configuration;
    }
}
