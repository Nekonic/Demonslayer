package nekonic;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
public class battleEvent implements Listener {
    @EventHandler
    public void rush(PlayerInteractEvent event){
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
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
                
            }
        }
    }
}