package me.eric.weathervote;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class ListenerClass implements Listener {
    @EventHandler
    public void invDragEvent(InventoryDragEvent e){
        Inventory inv = e.getInventory();
        String name = inv.getName();
        if(name.equals("天氣投票")){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void invClickEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        String name = inv.getName();
        if(name.equals("天氣投票")){
            e.setCancelled(true);
            int slot = e.getSlot();
            if(slot==1){
                VoteCount.voteCount(p, 1);
                p.closeInventory();
                return;
            }
            if(slot==4){
                VoteCount.voteCount(p, 2);
                p.closeInventory();
                return;
            }
            if(slot==7){
                VoteCount.voteCount(p,3);
                p.closeInventory();
                return;
            }
        }
        return;
    }
}

