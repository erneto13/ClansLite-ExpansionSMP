package cl_esmp.erneto1307.managers;

import cl_esmp.erneto1307.ClansLiteExpansionSMP;
import cl_esmp.erneto1307.model.CustomItem;
import cl_esmp.erneto1307.utils.MessageUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomItemManager {

    private ClansLiteExpansionSMP plugin;
    private ArrayList<CustomItem> items;

    public CustomItemManager(ClansLiteExpansionSMP plugin){
        this.plugin = plugin;
        this.items = new ArrayList<>();
    }

    public ArrayList<CustomItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CustomItem> items) {
        this.items = items;
    }

    public ItemStack getItemPathName(String pathName, String clanName){
        for (CustomItem item : items){
            if (item.getPath().equals(pathName)){
                return createItem(item, clanName);
            }
        }
        return null;
    }

    // Methods
    public ItemStack createItem(CustomItem customItem, String clanName){

        String icon = customItem.getIcon();
        ItemStack item = null;

        // Custom Heads Minecraft
        if (icon.startsWith("eyJ")){
            item = new ItemStack(Material.PLAYER_HEAD);

            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures",new Property("textures", icon));

            try {

                Field profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, profile);

            } catch(IllegalArgumentException|NoSuchFieldException|SecurityException|IllegalAccessException error) {
                error.printStackTrace();
            }
            item.setItemMeta(skullMeta);
        } else {
            item = new ItemStack(Material.valueOf(icon));
        }

        ItemMeta meta = item.getItemMeta();

        if(customItem.getDisplayName() != null) {
            meta.setDisplayName(MessageUtils.getColoredMessage(customItem.getDisplayName().replace("%clan%", clanName + "")));
        }

        if (customItem.getLore() != null){

            List<String> lore = new ArrayList<>(customItem.getLore());
            for (int i = 0; i < lore.size(); i++){
                lore.set(i, MessageUtils.getColoredMessage(lore.get(i).replace("%clan%", clanName)));
            }
            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }
}
