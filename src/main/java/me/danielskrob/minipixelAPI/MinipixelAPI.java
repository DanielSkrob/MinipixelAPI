package me.danielskrob.minipixelAPI;

import me.danielskrob.minipixelAPI.command_lock.CommandLockEvents;
import me.danielskrob.minipixelAPI.events.PlayerEvents;
import me.danielskrob.minipixelAPI.items.SpecialItem;
import me.danielskrob.minipixelAPI.menus.MenuListener;
import me.danielskrob.minipixelAPI.placeholders.PlaceholderManager;
import me.danielskrob.minipixelAPI.utils.PlayerUtils;
import me.danielskrob.minipixelAPI.utils.SpectatorManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinipixelAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        PlaceholderManager.init();
        SpectatorManager.init(this);
        PlayerUtils.init(this);
        SpecialItem.startPickupTask(this);

        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new CommandLockEvents(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }
}
