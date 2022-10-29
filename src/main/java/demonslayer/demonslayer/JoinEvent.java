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
    int count = 1;
    HashMap<UUID,Integer> map = new HashMap<UUID,Integer>();
    @EventHandler
    public  void Join(PlayerJoinEvent event){
        Player p = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        if(map.containsKey(uuid)) {
            map.put(uuid, map.get(uuid) + 1);
        } else {
            map.put(uuid, 1);
        }
        int count = map.get(uuid);
        event.setJoinMessage(ChatColor.BLUE+p.getName()+ChatColor.LIGHT_PURPLE + "님이 서버에 접속하셨습니다.\n"+ChatColor.BLUE+p.getName()+ChatColor.WHITE +"님은 "+ChatColor.DARK_RED+count+ChatColor.WHITE+"번째 플레이어 입니다!"  );
    }
    @EventHandler
    public  void Kick(PlayerKickEvent event){
        Player p = event.getPlayer();
        event.setReason(ChatColor.WHITE+"플레이어 : "+ChatColor.BLUE+p.getName()+ChatColor.WHITE+"\n킥 사유 : "+ChatColor.DARK_RED+event.getReason());
    }
    @EventHandler
    public void Interact(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Action a = event.getAction();
        if((a == Action.RIGHT_CLICK_BLOCK||a == Action.RIGHT_CLICK_AIR) && (event.getItem().getType() == Material.STICK)){
            p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 2.0F, 2.0F);
            p.setVelocity(p.getLocation().getDirection());

        }
        if((a == Action.LEFT_CLICK_BLOCK||a == Action.LEFT_CLICK_AIR) && (event.getItem().getType() == Material.DIAMOND_SWORD)){
            p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 2.0F, 2.0F);
            p.setVelocity(p.getLocation().getDirection());
        }
    }
}