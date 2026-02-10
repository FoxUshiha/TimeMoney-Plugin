package com.foxsrv.timemoney;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PrisonManager {

    private static final Set<UUID> presos = new HashSet<>();

    public static boolean estaPreso(Player p) {
        return presos.contains(p.getUniqueId());
    }

    public static void prender(Player p) {

        if (p.hasPermission("time.bypass")) return;

        if (estaPreso(p)) return;

        // Se já estiver em espectador, só marca como preso
        if (p.getGameMode() != GameMode.SPECTATOR) {
            p.setGameMode(GameMode.SPECTATOR);
        }

        presos.add(p.getUniqueId());

        p.sendMessage("§cYou ren out of time.");
    }

  public static void reviver(Player p) {

    if (!estaPreso(p)) return;

    presos.remove(p.getUniqueId());

    // Volta para survival diretamente
    p.setGameMode(GameMode.SURVIVAL);

    Location respawn = p.getBedSpawnLocation();

    if (respawn == null) {
        respawn = Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    p.teleport(respawn);

    p.sendMessage("§aVocê recebeu tempo e voltou à vida!");
  }
}