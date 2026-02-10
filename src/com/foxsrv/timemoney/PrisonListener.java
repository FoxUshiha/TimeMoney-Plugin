package com.foxsrv.timemoney;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class PrisonListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();

        double saldo = TimeMoney.get().getEconomy().getBalance(p);

        if (saldo <= 0) {
            PrisonManager.prender(p);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (PrisonManager.estaPreso(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cYou are dead!");
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (PrisonManager.estaPreso(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (PrisonManager.estaPreso(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cYou are dead!");
        }
    }
}
