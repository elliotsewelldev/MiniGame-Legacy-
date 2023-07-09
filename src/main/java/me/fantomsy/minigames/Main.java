package me.fantomsy.minigames;

import lombok.Getter;
import lombok.Setter;
import me.fantomsy.minigames.brawlgame.BrawlManager;
import me.fantomsy.minigames.commands.ClearDatapoints;
import me.fantomsy.minigames.commands.QuickGamemode;
import me.fantomsy.minigames.commands.Start;
import me.fantomsy.minigames.commands.Stop;
import me.fantomsy.minigames.events.Damage;
import me.fantomsy.minigames.events.ItemInteractions;
import me.fantomsy.minigames.events.Join;
import me.fantomsy.minigames.events.WorldProtect;
import me.fantomsy.minigames.gamehandler.GameState;
import me.fantomsy.minigames.managers.GameManager;
import me.fantomsy.minigames.managers.LocationsManager;
import me.fantomsy.minigames.managers.PlayerManager;
import me.fantomsy.minigames.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    private PlayerManager playerManager;
    private GameManager gameManager;
    private LocationsManager locationsManager;

    // Game Managers.
    private BrawlManager brawlManager;

    @Setter
    private GameState gameState;

    @Override
    public void onEnable() {
        instance = this;
        gameState = GameState.LOBBY;
        playerManager = new PlayerManager(this);
        gameManager = new GameManager(this);
        locationsManager = new LocationsManager(this);

        // Game Managers.

        brawlManager = new BrawlManager(this);
        loadEvents();
        loadCommands();
        Color.log("Minigames Ready");
    }

    @Override
    public void onDisable() {
    }

    private void loadEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Join(), this);
        pm.registerEvents(new Damage(), this);
        pm.registerEvents(new WorldProtect(), this);
        pm.registerEvents(new ItemInteractions(), this);
    }

    private void loadCommands() {
        getCommand("gstart").setExecutor(new Start());
        getCommand("gstop").setExecutor(new Stop());
        getCommand("clear").setExecutor(new ClearDatapoints());
        getCommand("g").setExecutor(new QuickGamemode());
    }
}
