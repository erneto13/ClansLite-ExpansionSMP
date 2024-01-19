package cl_esmp.erneto1307.utils;

import org.bukkit.Bukkit;

public class SupplementaryUtils {

    public static boolean newestServer() {
        if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17") ||
                Bukkit.getVersion().contains("1.18") || Bukkit.getVersion().contains("1.19") ||
                Bukkit.getVersion().contains("1.20.2")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean legacyServer() {
        if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") ||
                Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16") ||
                Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.18") ||
                Bukkit.getVersion().contains("1.19") || Bukkit.getVersion().contains("1.20")) {
            return false;
        } else {
            return true;
        }
    }

}
