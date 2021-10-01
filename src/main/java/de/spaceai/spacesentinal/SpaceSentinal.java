package de.spaceai.spacesentinal;

import de.spaceai.spacesentinal.api.SpaceSentinalAPI;
import de.spaceai.spacesentinal.command.InfoCommand;
import de.spaceai.spacesentinal.command.MoneyCommand;
import de.spaceai.spacesentinal.listener.PlayerJoinListener;
import de.spaceai.spacesentinal.service.ServiceManager;
import de.spaceai.spacesentinal.service.impl.ConfigService;
import de.spaceai.spacesentinal.service.impl.EconomyService;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SpaceSentinal extends JavaPlugin {

    private ServiceManager serviceManager;

    @Override
    public void onEnable() {

        this.serviceManager = new ServiceManager(this);
        this.serviceManager.registerService(new EconomyService("economyService"));
        this.serviceManager.registerService(new ConfigService("configService"));

        this.serviceManager.<ConfigService>getService("configService").createIfNotExist();

        SpaceSentinalAPI spaceSentinalAPI = new SpaceSentinalAPI(this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        // -- money command
        PluginCommand pluginCommand = getCommand("money");
        pluginCommand.setExecutor(new MoneyCommand(this));
        pluginCommand.setTabCompleter(new MoneyCommand(this));

        // -- gamemode command
        pluginCommand = getCommand("gamemode");

        getCommand("info").setExecutor(new InfoCommand(this));

    }

    @Override
    public void onDisable() {

    }

    public ServiceManager getServiceManager() {
        return serviceManager;
    }
}
