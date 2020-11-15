package me.uselessmnemonic.whistles.pets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import me.uselessmnemonic.whistles.melody.Melody;
import me.uselessmnemonic.whistles.melody.MelodyRunnable;
import me.uselessmnemonic.whistles.melody.Note;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sittable;
import org.bukkit.entity.Tameable;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class SitWhistle extends AbstractWhistle {

    private static final Melody MELODY = new Melody(
        Instrument.FLUTE, 2,
        Note.D0,
        Note.B0,
        Note.G0
    );

    public SitWhistle() {
        super(
            new SlimefunItemStack("SIT_WHISTLE", Material.BAMBOO, ChatColor.YELLOW + "Makes the player's pets sit down."),
            new ItemStack[] {
                new ItemStack(Material.BAMBOO),         null, null,
                new ItemStack(Material.IRON_NUGGET),    null, null,
                null,                                   null, null
            }
        );
    }

    @Override
    public void onWhistleBlow(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        MelodyRunnable melodyRunnable = new MelodyRunnable(MELODY);
        melodyRunnable.play(player);
        List<Entity> nearbyEntities = player.getNearbyEntities(20, 20, 20);
        nearbyEntities.stream()
                .filter((e) -> e instanceof Tameable && e instanceof Sittable)
                .map((e) -> (Sittable & Tameable)e)
                .filter((e) -> e.isTamed() && Objects.equals(e.getOwner(), player))
                .forEach((e) -> e.setSitting(true));
    }
}
