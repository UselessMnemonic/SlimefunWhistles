package me.uselessmnemonic.whistles.pets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ScareWhistle extends AbstractWhistle {

    public ScareWhistle() {
        super(
            new SlimefunItemStack("SIT_WHISTLE", Material.BAMBOO, ChatColor.YELLOW + "Scares away aggressive animals."),
            new ItemStack[] {
                    null, null, null,
                    null, null, null,
                    null, new ItemStack(Material.BAMBOO), new ItemStack(Material.PUMPKIN_SEEDS)
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
