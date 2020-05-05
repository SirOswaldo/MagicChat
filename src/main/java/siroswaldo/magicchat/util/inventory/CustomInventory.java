package siroswaldo.magicchat.util.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomInventory {

    private final String title;
    private final int size;
    private Inventory inventory;

    // Items
    public CustomInventory(JavaPlugin plugin, String title, int size) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.size = size;
        this.inventory = Bukkit.createInventory(null, this.size, this.title);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setBackgroundItems(ItemStack item) {
        for (int i = 0; i < size; i++) {
            setItem(i, item);
        }
    }

    public void setItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }
}