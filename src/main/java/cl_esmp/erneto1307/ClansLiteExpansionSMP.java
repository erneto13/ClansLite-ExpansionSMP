package cl_esmp.erneto1307;

import cl_esmp.erneto1307.commands.MainCommand;
import cl_esmp.erneto1307.configs.MainConfigManager;
import cl_esmp.erneto1307.managers.CustomItemManager;
import cl_esmp.erneto1307.model.CustomItem;
import cl_esmp.erneto1307.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ClansLiteExpansionSMP extends JavaPlugin {

    // Instances
    private CustomItemManager customItemManager;

    private MainConfigManager mainConfigManager;


    // Variables, constants, constructors
    private final ConsoleCommandSender log = Bukkit.getConsoleSender();

    private final PluginDescriptionFile file = getDescription();
    public final String version = file.getVersion();

    public final String prefix = "&8[#e1e1e1ClansLite-ExpansionSMP&8] ";

    public void onEnable(){

        // TASK
        this.customItemManager = new CustomItemManager(this);

        // Configuration Manager
        this.mainConfigManager = new MainConfigManager(this);
        this.mainConfigManager.load();

        // Register schedule commands
        scheduleCommands(this);

        // Register schedule events

        if (Bukkit.getPluginManager().isPluginEnabled("ClansLite")){
            log.sendMessage(MessageUtils.getColoredMessage("[ClansLite-ExpansionSMP] The expansion for the ClansLite plugin has been loaded successfully. Developed by erneto1307. v" + version));
        } else {
            log.sendMessage(MessageUtils.getColoredMessage("#e93630The expansion for the ClansLite plugin could not be loaded because the required plugin is not installed."));
        }
    }

    public void onDisable(){
        log.sendMessage(MessageUtils.getColoredMessage("#e93630The expansion for the ClansLite plugin has been successfully disabled. Developed by erneto1307. v" + version));
    }

    public void scheduleCommands(ClansLiteExpansionSMP plugin) {
        this.getCommand("cl-exsmp").setExecutor(new MainCommand(plugin));
    }

    // Getter's
    public CustomItemManager getCustomItemManager(){
        return customItemManager;
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }
}
