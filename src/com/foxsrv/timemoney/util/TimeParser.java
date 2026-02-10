package com.foxsrv.timemoney.util;

public class TimeParser {

    public static long parse(String input) {

        input = input.toLowerCase();

        if (input.contains(":")) {
            String[] split = input.split(":");
            return Long.parseLong(split[0]) * 60
                    + Long.parseLong(split[1]);
        }

        long total = 0;

        try {

            if (input.endsWith("d"))
                total += Long.parseLong(input.replace("d", "")) * 86400;

            else if (input.endsWith("h"))
                total += Long.parseLong(input.replace("h", "")) * 3600;

            else if (input.endsWith("m"))
                total += Long.parseLong(input.replace("m", "")) * 60;

            else if (input.endsWith("s"))
                total += Long.parseLong(input.replace("s", ""));

            else
                total = Long.parseLong(input);

        } catch (Exception e) {
            return 0;
        }

        return total;
    }
}
