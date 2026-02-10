package com.foxsrv.timemoney;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class TimeTask implements Runnable {

    @Override
    public void run() {

        for (Player p : Bukkit.getOnlinePlayers()) {

            double saldo = TimeMoney.get().getEconomy().getBalance(p);

            if (saldo > 0) {
                TimeMoney.get().getEconomy().withdrawPlayer(p, 1);
            }

            // Tempo acabou
            if (saldo <= 1) {

                if (!p.hasPermission("time.bypass")) {

                    // Só mata se estiver jogando
                    if (p.getGameMode() == GameMode.SURVIVAL ||
                        p.getGameMode() == GameMode.ADVENTURE) {

                        p.setHealth(0);
                    }
                }
            }

            // Se estava preso e recebeu tempo novamente
            if (saldo > 1 && PrisonManager.estaPreso(p)) {

                // Apenas reviver normalmente, sem matar de novo
                PrisonManager.reviver(p);
            }
        }
    }
}
