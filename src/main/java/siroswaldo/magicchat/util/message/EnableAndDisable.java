package siroswaldo.magicchat.util.message;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EnableAndDisable {

    private final JavaPlugin javaPlugin;
    private final List<String> enable;
    private final List<String> disable;

    public EnableAndDisable(JavaPlugin javaPlugin){
        this.javaPlugin = javaPlugin;
        // Enable
        enable = new ArrayList<>();
        enable.add("&bMagic&9Chat &e>>> &aComplemento cargado!");
        // Disable
        disable = new ArrayList<>();
        disable.add("&bMagic&9Chat &e>>> &cComplemento descargado!");
    }

    public void sendEnable(){
        for (String line:enable){
            javaPlugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

    public void sendDisable(){
        for (String line:disable){
            javaPlugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }
}
