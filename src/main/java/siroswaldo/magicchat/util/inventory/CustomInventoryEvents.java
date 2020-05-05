package siroswaldo.magicchat.util.inventory;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.magicchat.util.inventory.interfaces.InventoryEvents;
import siroswaldo.magicchat.util.inventory.tasks.InventoryOpenTask;

public class CustomInventoryEvents implements Listener, InventoryEvents {

    private final JavaPlugin plugin;
    private final String title;

    public CustomInventoryEvents(JavaPlugin plugin, String title) {
        this.plugin = plugin;
        this.title = ChatColor.translateAlternateColorCodes('&', title);
    }

    public String getTitle() {
        return title;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String inventoryTitle = event.getView().getTitle();
        if (inventoryTitle.equals(title) && event.getInventory().getType().equals(InventoryType.CHEST)) {
            onClickEvent(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        String inventoryTitle = event.getView().getTitle();
        if (inventoryTitle.equals(title)) {
            if (onCloseEvent(event)) {
                InventoryOpenTask inventoryOpenTask = new InventoryOpenTask(plugin, (Player) event.getPlayer(), event.getInventory(), 1);
                inventoryOpenTask.startScheduler();
            }
        }
    }

    /**
     * Esta funcion se llama cuando el jugador hace click con el mouse en el
     * inventario.
     *
     * @param event : Evento involucrado en el click del inventario
     */
    public void onClickEvent(InventoryClickEvent event) {
    }

    /**
     * Esta funcion se llama cuando el usuario cierra el inventario
     *
     * @param event : Evento involucrado en el cierre del inventario
     * @return boolean : Si es verdadero abre nuevamente el inventario. Si es
     * falso no abre el inventario
     */
    public boolean onCloseEvent(InventoryCloseEvent event) {
        return false;
    }
}
