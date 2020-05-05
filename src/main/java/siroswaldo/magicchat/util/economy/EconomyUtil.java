package siroswaldo.magicchat.util.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyUtil {

    private final JavaPlugin javaPlugin;
    private Economy economy;
    private final boolean enable;

    public EconomyUtil(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        enable = setupEconomy();
    }

    public Economy getEconomy() {
        return economy;
    }

    public boolean isEnable() {
        return enable;
    }

    private boolean setupEconomy() {
        if (javaPlugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = javaPlugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
}