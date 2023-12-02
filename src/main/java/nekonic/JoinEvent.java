package nekonic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;


public class JoinEvent implements Listener {
    @EventHandler
    public void Join(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String welcomeMessage = ChatColor.BLUE + p.getName() + ChatColor.LIGHT_PURPLE + "님이 서버에 접속하셨습니다.\n" + ChatColor.BLUE + p.getName();
        Bukkit.getLogger().info(welcomeMessage);
    }

    @EventHandler
    public void Kick(PlayerKickEvent event) {
        Player p = event.getPlayer();
        event.setReason(ChatColor.WHITE + "플레이어 : " + ChatColor.BLUE + p.getName() + ChatColor.WHITE + "\n킥 사유 : " + ChatColor.DARK_RED + event.getReason());
    }
}