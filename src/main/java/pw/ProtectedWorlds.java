package pw;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.block.BlockIgniteEvent;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerBucketEmptyEvent;
import cn.nukkit.event.player.PlayerBucketFillEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class ProtectedWorlds extends PluginBase implements Listener {

    Config config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void action(BlockBreakEvent e) {
        if (config.getStringList("worlds").contains(e.getBlock().getLevel().getName()) && !e.getPlayer().hasPermission("protectedworlds.bypass." + e.getPlayer().getLevel().getName().toLowerCase()) && !e.getPlayer().hasPermission("protectedworlds.bypassall") && config.getBoolean("noBlockBreaking")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void action(BlockPlaceEvent e) {
        if (config.getStringList("worlds").contains(e.getBlock().getLevel().getName()) && !e.getPlayer().hasPermission("protectedworlds.bypass." + e.getPlayer().getLevel().getName().toLowerCase()) && !e.getPlayer().hasPermission("protectedworlds.bypassall") && config.getBoolean("noBlockPlacing")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void action(PlayerBucketFillEvent e) {
        if (config.getStringList("worlds").contains(e.getPlayer().getLevel().getName()) && !e.getPlayer().hasPermission("protectedworlds.bypass." + e.getPlayer().getLevel().getName().toLowerCase()) && !e.getPlayer().hasPermission("protectedworlds.bypassall") && config.getBoolean("noBucketFilling")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void action(PlayerBucketEmptyEvent e) {
        if (config.getStringList("worlds").contains(e.getPlayer().getLevel().getName()) && !e.getPlayer().hasPermission("protectedworlds.bypass." + e.getPlayer().getLevel().getName().toLowerCase()) && !e.getPlayer().hasPermission("protectedworlds.bypassall") && config.getBoolean("noBucketEmptying")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void action(PlayerDropItemEvent e) {
        if (config.getStringList("worlds").contains(e.getPlayer().getLevel().getName()) && !e.getPlayer().hasPermission("protectedworlds.bypass." + e.getPlayer().getLevel().getName().toLowerCase()) && !e.getPlayer().hasPermission("protectedworlds.bypassall") && config.getBoolean("noItemDropping")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void action(BlockIgniteEvent e) {
        if (config.getStringList("worlds").contains(e.getBlock().getLevel().getName()) && config.getBoolean("noBlockIgniting") && e.getEntity() instanceof Player) {
            if (!((Player) e.getEntity()).hasPermission("protectedworlds.bypass." + e.getEntity().getLevel().getName().toLowerCase()) && !((Player) e.getEntity()).hasPermission("protectedworlds.bypassall")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void action(PlayerInteractEvent e) {
        if (config.getStringList("worlds").contains(e.getPlayer().getLevel().getName()) && !e.getPlayer().hasPermission("protectedworlds.bypass." + e.getPlayer().getLevel().getName().toLowerCase()) && !e.getPlayer().hasPermission("protectedworlds.bypassall")) {
            if (e.getAction() == PlayerInteractEvent.Action.PHYSICAL && e.getBlock().getId() == Block.FARMLAND && config.getBoolean("noFarmlandJumping")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void action(EntityDamageByEntityEvent e) {
        if (config.getStringList("worlds").contains(e.getEntity().getLevel().getName()) && e.getEntity() instanceof Player && e.getDamager() instanceof Player && config.getBoolean("noPvp")) {
            if (!((Player) e.getDamager()).hasPermission("protectedworlds.bypass." + e.getDamager().getLevel().getName().toLowerCase()) && !((Player) e.getDamager()).hasPermission("protectedworlds.bypassall")) {
                e.setCancelled(true);
            }
        }
    }
}