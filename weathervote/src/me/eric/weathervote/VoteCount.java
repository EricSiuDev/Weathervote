package me.eric.weathervote;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class VoteCount {
    public static HashMap<Player, Integer> weathervote = new HashMap<>();
    public static int c = 0;
    public static int r = 0;
    public static int t = 0;
    public static void voteReset(){
        c = 0;
        r = 0;
        t = 0;
        weathervote.clear();
    }
    public static void voteCount(Player p, int v){
        if(weathervote.containsKey(p)){
            p.sendMessage(ChatColor.BLUE+"[天氣投票] 你已經投票了!");
            return;
        }
        if(!(weathervote.containsKey(p))){
            weathervote.put(p, v);
        }
        if(v==1){
            c=c+1;
            p.sendMessage(ChatColor.BLUE+"[天氣投票] 成功投票!");
            Bukkit.broadcastMessage(ChatColor.BLUE+"[天氣投票] 晴天的票數+1! 使用/weathervote 進行投票");
        }
        if(v==2){
            r=r+1;
            p.sendMessage(ChatColor.BLUE+"[天氣投票] 成功投票!");
            Bukkit.broadcastMessage(ChatColor.BLUE+"[天氣投票] 雨天的票數+1! 使用/weathervote 進行投票");
        }
        if(v==3){
            t=t+1;
            p.sendMessage(ChatColor.BLUE+"[天氣投票] 成功投票!");
            Bukkit.broadcastMessage(ChatColor.BLUE+"[天氣投票] 雷暴的票數+1! 使用/weathervote 進行投票");
        }
        int playercount = Bukkit.getOnlinePlayers().size();
        if(c>=(playercount/4.0)){
            Bukkit.broadcastMessage(ChatColor.BLUE+"[天氣投票] 4分之1的玩家投了晴天 已通過!");
            for(World world : Bukkit.getServer().getWorlds()){
                world.setStorm(false);
                world.setThundering(false);
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
            voteReset();
            return;
        }
        if(r>=(playercount/4.0)){
            Bukkit.broadcastMessage(ChatColor.BLUE+"[天氣投票] 4分之1的玩家投了雨天 已通過!");
            for(World world : Bukkit.getServer().getWorlds()){
                world.setStorm(true);
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather rain");
            voteReset();
            return;
        }
        if(t>=(playercount/4.0)){
            Bukkit.broadcastMessage(ChatColor.BLUE+"[天氣投票] 4分之1的玩家投了雷暴 已通過!");
            for(World world : Bukkit.getServer().getWorlds()){
                world.setThundering(true);
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather thunder");
            voteReset();
            return;
        }
    }
    public static void weatherVote(Player p){
        Inventory weatherinv = Bukkit.createInventory(null, 9, "天氣投票");
        ItemStack clear = new ItemStack(Material.GREEN_STAINED_GLASS, (c+1));
        ItemMeta clearmeta = clear.getItemMeta();
        clearmeta.setDisplayName(ChatColor.BLUE+"晴天");
        clear.setItemMeta(clearmeta);
        weatherinv.setItem(1, clear);
        ItemStack rain = new ItemStack(Material.YELLOW_STAINED_GLASS, (r+1));
        ItemMeta rainmeta = rain.getItemMeta();
        rainmeta.setDisplayName(ChatColor.BLUE+"雨天");
        rain.setItemMeta(rainmeta);
        weatherinv.setItem(4, rain);
        ItemStack thunder = new ItemStack(Material.RED_STAINED_GLASS, (t+1));
        ItemMeta thundermeta = thunder.getItemMeta();
        thundermeta.setDisplayName(ChatColor.BLUE+"雷暴");
        thunder.setItemMeta(thundermeta);
        weatherinv.setItem(7, thunder);
        p.openInventory(weatherinv);
    }
}
