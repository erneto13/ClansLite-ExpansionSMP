package cl_esmp.erneto1307.managers;

import cl_esmp.erneto1307.ClansLiteExpansionSMP;
import cl_esmp.erneto1307.model.CustomItem;
import cl_esmp.erneto1307.utils.MessageUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
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

    public CustomItemManager(ClansLiteExpansionSMP plugin) {
        this.plugin = plugin;
        this.items = new ArrayList<>();
    }

    public ArrayList<CustomItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CustomItem> items) {
        this.items = items;
    }

    public ItemStack getItemPathName(String pathName, String clanName) {
        for (CustomItem item : items) {
            if (item.getPath().equals(pathName)) {
                return createItem(item, clanName);
            }
        }
        return null;
    }

    // Methods
    public ItemStack createItem(CustomItem customItem, String clanName) {

        // Fields
        String icon = customItem.getIcon();
        ItemStack item = null;

        // Custom Heads Minecraft
        if (icon.startsWith("eyJ")) {
            item = new ItemStack(Material.PLAYER_HEAD);

            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", icon));

            try {

                Field profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, profile);

            } catch (IllegalArgumentException | NoSuchFieldException | SecurityException |
                     IllegalAccessException error) {
                error.printStackTrace();
            }
            item.setItemMeta(skullMeta);
        } else { // Default Minecraft Material. Ex: RED_BANNER
            item = new ItemStack(Material.valueOf(icon));
        }

        // Create a meta to properties items
        ItemMeta meta = item.getItemMeta();

        // Show a title. Ex: items.display-name: MyClan Folks
        if (customItem.getDisplayName() != null) {
            String displayName = customItem.getDisplayName().replace("%clan%", clanName);

            // Replace PlaceholderAPI variables if they exist
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                displayName = PlaceholderAPI.setPlaceholders((OfflinePlayer) null, displayName);
            }

            meta.setDisplayName(MessageUtils.getColoredMessage(displayName));
        }

        // Show a multi-line messages. Ex: items.lore: This is my clan.
        // Join now please!
        if (customItem.getLore() != null) {

            List<String> lore = new ArrayList<>(customItem.getLore());

            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                for (int i = 0; i < lore.size(); i++) {
                    lore.set(i, PlaceholderAPI.setPlaceholders((OfflinePlayer) null, MessageUtils.getColoredMessage(lore.get(i))));
                }
            }

            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }
}
