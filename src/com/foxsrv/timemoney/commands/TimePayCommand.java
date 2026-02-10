package com.foxsrv.timemoney.commands;

import com.foxsrv.timemoney.TimeFormatter;
import com.foxsrv.timemoney.TimeMoney;
import com.foxsrv.timemoney.util.TimeParser;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimePayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        if (args.length < 2) {
            p.sendMessage("/timepay <nick> <tempo>");
            return true;
        }

        Player alvo = Bukkit.getPlayer(args[0]);

        if (alvo == null) {
            p.sendMessage("Jogador offline.");
            return true;
        }

        long segundos = TimeParser.parse(args[1]);

        if (segundos <= 0) {
            p.sendMessage("Tempo inválido.");
            return true;
        }

        var eco = TimeMoney.get().getEconomy();

        if (eco.getBalance(p) < segundos) {
            p.sendMessage("Você não tem tempo suficiente.");
            return true;
        }

        eco.withdrawPlayer(p, segundos);
        eco.depositPlayer(alvo, segundos);

        p.sendMessage("Você enviou "
                + TimeFormatter.format(segundos)
                + " para " + alvo.getName());

        return true;
    }
}
