/*
   Maven
    <repositories>
        <repository>
            <id>placeholderapi</id>
            <url>https://repo.extendedclip.com/content/repositories/placeholderapi/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
         <groupId>me.clip</groupId>
          <artifactId>placeholderapi</artifactId>
          <version>{VERSION}</version>
         <scope>provided</scope>
        </dependency>
    </dependencies>
 */
package siroswaldo.magicchat.util.placeholderapi;


import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlaceHolderApiUtil {

    private boolean enable;

    public PlaceHolderApiUtil(JavaPlugin plugin){
        enable = plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public boolean isEnable() {
        return enable;
    }

    public String setPlaceholders(Player player, String text){
        return PlaceholderAPI.setPlaceholders(player, text);
    }
}
