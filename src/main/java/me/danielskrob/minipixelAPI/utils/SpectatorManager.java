package me.danielskrob.minipixelAPI.utils;

import me.danielskrob.minipixelAPI.MinipixelAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpectatorManager {

    private static MinipixelAPI plugin;

    public static void init(MinipixelAPI api) {
        plugin = api;
    }

    public static void hideFromEveryone(Player target) {
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            viewer.hidePlayer(plugin, target);
        }

        target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1, false, false));
    }

    public static void showToEveryone(Player target) {
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            viewer.showPlayer(plugin, target);
        }
    }
}
