package demonslayer.demonslayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Demonslayer extends JavaPlugin {
    @Override
    public void onEnable(){
        Event();
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "플러그인이 활성화 되었습니다.");
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "플러그인이 비활성화 되었습니다.");
    }

    public void Event(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
    }
}