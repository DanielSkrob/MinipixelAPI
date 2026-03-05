# MinipixelAPI

MinipixelAPI is a modular API designed for Minecraft minigame servers.
It provides utilities for building independent game modules.

## Features

-   Module integration system
-   Spectator management
-   Player spawn countdowns
-   Special item spawning and pickups
-   PlaceholderAPI integration
-   Scoreboard management
-   Command locking
-   GUI menu utilities

------------------------------------------------------------------------

# Installation

1.  Build or download the MinipixelAPI jar.
2.  Place it into your server's `plugins/` folder.
3.  Restart the server.

Modules can now depend on the API.

------------------------------------------------------------------------

# Module System

Modules extend the game using the `Module` interface.

``` java
public interface Module {
    void onEnable(Plugin plugin, String data, CoreBridge bridge);
    String getName();
}
```

### Parameters

  Parameter   Description
  ----------- --------------------------------
  plugin      Main plugin instance
  data        Optional configuration
  bridge      Communication bridge with core

------------------------------------------------------------------------

# CoreBridge

The bridge allows modules to interact with the game instance.

``` java
public interface CoreBridge {
    void endInstance();
    Set<Player> getActivePlayers();
    void endGameForPlayer(Player player, String message);
}
```

------------------------------------------------------------------------

# Example Module

``` java
public class ExampleModule implements Module {

    @Override
    public void onEnable(Plugin plugin, String data, CoreBridge bridge) {
        for (Player player : bridge.getActivePlayers()) {
            player.sendMessage("Example module loaded!");
        }
    }

    @Override
    public String getName() {
        return "ExampleModule";
    }
}
```

------------------------------------------------------------------------

# Player Utilities

Spawn players with a countdown.

``` java
PlayerApiUtils.spawnPlayer(player, spawnLocation, countdownSeconds);
```

Behavior:

1.  Teleports player
2.  Starts countdown title
3.  Player becomes invulnerable
4.  Movement locked
5.  "GO!" displayed when countdown ends

------------------------------------------------------------------------

# Spectator Manager

Make a player spectator:

``` java
SpectatorManager.hideFromEveryone(player);
```

Return player from spectator:

``` java
SpectatorManager.showToEveryone(player);
```

Check spectator:

``` java
SpectatorManager.isSpectator(player);
```

------------------------------------------------------------------------

# Special Items

Create collectible in‑game items by extending `SpecialItem`.

``` java
public class HealthOrb extends SpecialItem {

    public HealthOrb(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getId() {
        return "health_orb";
    }

    @Override
    public Material getMaterial() {
        return Material.EMERALD;
    }

    @Override
    public String getName() {
        return "&aHealth Orb";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Restores health");
    }

    @Override
    public String getFloatingName() {
        return "<green>Health Orb";
    }
}
```

Spawn item:

``` java
healthOrb.spawnItem(location);
```

The API automatically handles holograms and pickup detection.

------------------------------------------------------------------------

# ItemManager

Identify special items:

``` java
SpecialItem item = ItemManager.getFromItem(itemStack);
```

------------------------------------------------------------------------

# Placeholder System

Register placeholders.

Global placeholder:

``` java
PlaceholderManager.register("map_name", () -> "LavaRun");
```

Player placeholder:

``` java
PlaceholderManager.register("lives", player -> {
    return String.valueOf(getLives(player));
});
```

Usage:

    %minipixelcore_map_name%
    %minipixelcore_lives%

------------------------------------------------------------------------

# Scoreboard Manager

Create scoreboard:

``` java
ScoreboardManager board = new ScoreboardManager();

board.setTitle("<gold>Minigame");

board.setLines(List.of(
    "<gray>Players: %minipixelcore_players%",
    "",
    "<green>Good luck!"
));
```

Show scoreboard:

``` java
board.showTo(player);
```

------------------------------------------------------------------------

# GUI Menus

Create menu:

``` java
BaseMenu menu = new BaseMenu(9, "Example Menu");
```

Add button:

``` java
menu.setItem(0, itemStack, event -> {
    Player player = (Player) event.getWhoClicked();
    player.sendMessage("Clicked!");
});
```

Open menu:

``` java
player.openInventory(menu.getInventory());
```

------------------------------------------------------------------------

# Command Lock

Allow command:

``` java
CommandLockManager.allowCommand("/msg");
```

Allow player bypass:

``` java
CommandLockManager.allowPlayer(player.getUniqueId());
```

------------------------------------------------------------------------

# Dependencies

Some features require:

-   PlaceholderAPI
-   FastBoard
-   MiniMessage
-   DecentHolograms

------------------------------------------------------------------------

# Best Practices

-   Instantiate SpecialItem classes on module startup
-   Use CoreBridge to control game state
-   Keep modules independent
-   Use placeholders for scoreboard values
