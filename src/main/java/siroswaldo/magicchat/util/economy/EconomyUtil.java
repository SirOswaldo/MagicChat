package siroswaldo.magicchat.util.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyUtil {

    private JavaPlugin plugin;
    private Economy econ;
    private boolean enable;

    public EconomyUtil(JavaPlugin plugin) {
        this.plugin = plugin;
        enable = setupEconomy();
    }

    public Economy getEconomy() {
        return econ;
    }

    public boolean isEnable() {
        return enable;
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}