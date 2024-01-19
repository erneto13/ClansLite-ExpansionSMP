package cl_esmp.erneto1307.model;

import java.util.List;

public class CustomItem {
    private String path;
    private String icon;
    private String displayName;
    private List<String> lore;

    // Constructor
    public CustomItem(String path, String icon) {
        this.path = path;
        this.icon = icon;
    }

    // Getter's and Setter's
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }
}
