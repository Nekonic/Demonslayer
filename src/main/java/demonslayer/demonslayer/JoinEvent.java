package demonslayer.demonslayer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.HashMap;
import java.util.UUID;

public class JoinEvent implements Listener {
    private Map<Player, Integer> joinOrderMap;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        joinOrderMap = new HashMap<>();
    }

    @EventHandler
    public  void Join(PlayerJoinEvent event){
        Player p = event.getPlayer();

        if (!joinOrderMap.containsKey(p)) {
            int count = joinOrderMap.size()+1;
            joinOrderMap.put(p,count);
        }

        String welcomeMessage = ChatColor.BLUE+p.getName()+ChatColor.LIGHT_PURPLE + "님이 서버에 접속하셨습니다.\n"+ChatColor.BLUE+p.getName()+ChatColor.WHITE +"님은 "+ChatColor.DARK_RED+count+ChatColor.WHITE+"번째 플레이어 입니다!";
        Bukkit.getLogger().info(welcomeMessage);
    }
    @EventHandler
    public  void Kick(PlayerKickEvent event){
        Player p = event.getPlayer();
        event.setReason(ChatColor.WHITE+"플레이어 : "+ChatColor.BLUE+p.getName()+ChatColor.WHITE+"\n킥 사유 : "+ChatColor.DARK_RED+event.getReason());
    }
}