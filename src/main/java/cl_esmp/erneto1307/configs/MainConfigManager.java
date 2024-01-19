package cl_esmp.erneto1307.configs;

import cl_esmp.erneto1307.ClansLiteExpansionSMP;
import cl_esmp.erneto1307.model.CustomItem;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class MainConfigManager {

    private ClansLiteExpansionSMP plugin;
    private CustomConfigs configFile;
    public MainConfigManager (ClansLiteExpansionSMP plugin){
        this.plugin = plugin;
    }

    public void load(){
        FileConfiguration config = configFile.getConfig();
        ArrayList<CustomItem> items = new ArrayList<>();

        for (String key : config.getConfigurationSection("items").getKeys(false)){
            String path = "items." + key;
            String id = config.getString(path + "icon");
            CustomItem customItem = new CustomItem(key,id);

            if (config.contains(path + ".lore")){
                customItem.setLore(config.getStringList(path + ".lore"));
            }

            items.add(customItem);
        }

        plugin.getCustomItemManager().setItems(items);
    }

    public void registerConfig(){
        configFile = new CustomConfigs("config.yml", plugin);
        configFile.registerConfig();
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        load();
    }
}
