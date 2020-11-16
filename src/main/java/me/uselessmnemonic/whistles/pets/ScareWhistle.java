package me.uselessmnemonic.whistles.pets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import me.uselessmnemonic.whistles.melody.Melody;
import me.uselessmnemonic.whistles.melody.Note;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScareWhistle extends AbstractWhistle {

    private static final Melody MELODY = new Melody(
        Instrument.FLUTE, 3,
        Note.C0,
        Note.D0,
        Note.E0,
        Note.G0,
        Note.C1
    );

    public ScareWhistle() {
        super(
            new SlimefunItemStack(
                "SCARE_WHISTLE",
                Material.BAMBOO,
                ChatColor.WHITE + "Scare Whistle",
                ChatColor.YELLOW + "Scares away wild Bears, Wolves, and Hoglins."
            ),
            new ItemStack[] {
                    new ItemStack(Material.BAMBOO), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.SPIDER_EYE),
                    null,                           null,                                null,
                    null,                           null,                                null
            }
        );
    }

    @Override
    public Melody getMelody() {
        return MELODY;
    }

    @Override
    public void onWhistleBlow(PlayerRightClickEvent event) {
        Player player = event.getPlayer();

        // find all hostile animals in the immediate area
        List<Entity> nearbyEntities = player.getNearbyEntities(10, 10, 10);
        List<Animals> animals = nearbyEntities.stream()
                .filter((e) -> e instanceof PolarBear | e instanceof Wolf | e instanceof Hoglin)
                .map((e) -> (Animals)e)
                .filter((e) -> {
                    if (e instanceof Tameable) {
                        Tameable t = (Tameable)e;
                        return !Objects.equals(t.getOwnerUniqueId(), player.getUniqueId());
                    }
                    else return false;
                })
                .collect(Collectors.toList());

        // scare away animals
        animals.forEach((e) -> {
            e.setTarget(null);
            e.getPathfinder().stopPathfinding();
            if (e instanceof Wolf) ((Wolf) e).setAngry(false);
            Vector randomDistance = Vector.getRandom().setY(0).normalize().multiply(20);
            Location dest = player.getLocation().add(randomDistance);
            e.getPathfinder().moveTo(dest, 1.5);
        });
    }
}
