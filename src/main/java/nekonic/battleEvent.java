package nekonic;

import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class battleEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action a = event.getAction();
        /*
        if ((a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR) && (event.getItem().getType() == Material.STICK)) {
            p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 2.0F, 2.0F);
            p.setVelocity(p.getLocation().getDirection());
        }
        */
        if (a == Action.LEFT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR) {
            mizu(p);
        }if ((a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR) && (event.getItem().getType() == Material.STICK)) {
            Location targetBlock = p.getTargetBlockExact(100).getLocation(); // 플레이어가 바라보는 블럭의 위치 가져오기 (최대 100 블럭)
            int numberOfFireballs = 20; // 생성할 화염구 개수
            int minDistance = 1; // 최소 거리
            int maxDistance = 15; // 최대 거리

            Random random = new Random();
            // 파티클로 범위 표시
            drawCircle(targetBlock, maxDistance, 100); // 원 그리기

            for (int i = 0; i < numberOfFireballs; i++) {
                double randomDistance = minDistance + (maxDistance - minDistance) * random.nextDouble(); // 최소와 최대 사이의 무작위 거리 설정
                double randomAngle = random.nextDouble() * Math.PI * 2; // 0부터 360도 사이의 무작위 각도 설정

                // 무작위한 위치 계산
                double randomX = randomDistance * Math.cos(randomAngle);
                double randomZ = randomDistance * Math.sin(randomAngle);

                Location spawnLocation = targetBlock.clone().add(randomX, 20+random.nextDouble(15), randomZ); // 무작위 위치 설정 (Y축은 조정 가능)

                // 화염구 생성 및 설정
                Fireball fireball = p.getWorld().spawn(spawnLocation, Fireball.class);
                fireball.setIsIncendiary(false); // 폭발 시 불붙지 않도록 설정
                fireball.setYield(5.0F); // 폭발의 위력 설정 (기본값은 1.0F)
                fireball.setDirection(new Vector(0, -1, 0)); // 화염구 이동 방향 설정
                fireball.setShooter(null); // 화염구 발사자
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player p = (Player) damager;
            mizu(p);
        }
    }

    private void mizu(Player p) {
        if (p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
            p.setVelocity(p.getLocation().getDirection());
            p.playSound(p.getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, 2.0F, 2.0F);
            p.spawnParticle(Particle.DRIPPING_WATER, p.getLocation(), 50, 4, 3, 4);
            p.spawnParticle(Particle.BUBBLE, p.getLocation(), 10, 1, 1, 1);
        }
    }
    private void drawCircle(Location targetBlock, double radius, int duration) {
        if (targetBlock != null) {
            World world = targetBlock.getWorld();
            for (int i = 0; i < duration; i++) {
                double angle = 2 * Math.PI * i / duration;
                double x = targetBlock.getX() + radius * Math.cos(angle);
                double z = targetBlock.getZ() + radius * Math.sin(angle);
                Location point = new Location(world, x, targetBlock.getY()+2, z);

                // 원 주변에 파티클 효과 추가
                world.spawnParticle(Particle.FLAME, point, 0, 0,0,0);
            }
        }
    }
}