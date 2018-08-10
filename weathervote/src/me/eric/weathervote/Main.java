package me.eric.weathervote;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("WeatherVote plugin has been enabled.");
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ListenerClass(), this);
        VoteCount.voteReset();
    }

    @Override
    public void onDisable() {
        getLogger().info("WeatherVote plugin has been disabled.");
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(command.getName().equalsIgnoreCase("weathervote")){
                VoteCount.weatherVote(p);
                return true;
            }
        }
        return false;
    }
}

