package siroswaldo.magicchat.util.inventory.interfaces;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface InventoryEvents {
    void onClickEvent(InventoryClickEvent event);
    boolean onCloseEvent(InventoryCloseEvent event);
}