package cl_esmp.erneto1307.managers;

import cl_esmp.erneto1307.ClansLiteExpansionSMP;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InventoryManager {

    private ClansLiteExpansionSMP plugin;
    private ArrayList<Player> players;

    public InventoryManager(ClansLiteExpansionSMP plugin){
        this.plugin = plugin;
        this.players = new ArrayList<>();
    }


}
