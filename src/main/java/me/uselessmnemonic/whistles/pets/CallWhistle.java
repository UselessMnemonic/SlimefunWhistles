package me.uselessmnemonic.whistles.pets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CallWhistle extends AbstractWhistle {

    public CallWhistle() {
        super(
            new SlimefunItemStack("CALL_WHISTLE", Material.BAMBOO, ChatColor.YELLOW + "Calls the player's pets."),
            new ItemStack[] {
                    null, null, null,
                    null, null, null,
                    null, new ItemStack(Material.BAMBOO), new ItemStack(Material.GOLD_NUGGET)
            }
        );
    }

    @Override
    public void onWhistleBlow(PlayerRightClickEvent event) {
        //TODO
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public void damageItem(Player p, ItemStack item) {
        //TODO
    }
}
