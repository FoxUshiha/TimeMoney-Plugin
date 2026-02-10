package com.foxsrv.timemoney;

public class TimeFormatter {

    public static String format(long s) {

        long dias = s / 86400;
        s %= 86400;

        long horas = s / 3600;
        s %= 3600;

        long minutos = s / 60;
        s %= 60;

        // Se tiver pelo menos 1 dia, mostra DDD:HH:MM:SS
        if (dias > 0) {
            return String.format("%03d:%02d:%02d:%02d",
                    dias, horas, minutos, s);
        }

        // Se tiver horas mas menos de 1 dia, mostra HH:MM:SS
        if (horas > 0) {
            return String.format("%02d:%02d:%02d",
                    horas, minutos, s);
        }

        // Se tiver menos de 1 hora, mostra MM:SS
        return String.format("%02d:%02d",
                minutos, s);
    }
}
