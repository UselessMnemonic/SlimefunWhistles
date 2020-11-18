package me.uselessmnemonic.whistles.animals;

import com.destroystokyo.paper.entity.ai.*;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import me.uselessmnemonic.whistles.Whistles;
import me.uselessmnemonic.whistles.melody.Melody;
import me.uselessmnemonic.whistles.melody.Note;
import me.uselessmnemonic.whistles.persistence.PersistentDataBoolean;
import me.uselessmnemonic.whistles.persistence.PersistentDataLocation;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class ScareWhistle extends AbstractWhistle {

    private static final Melody MELODY = new Melody(
        Instrument.XYLOPHONE, 2,
            Note.C1,
            Note.DSh1,
            Note.FSh1,
            Note.ASh1,
            Note.FSh1
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
    public Melody getMelody(ItemStack stack) {
        return MELODY;
    }

    @Override
    public void onWhistleBlow(PlayerRightClickEvent event) {
        Player player = event.getPlayer();

        // find all hostile animals in the immediate area
        List<Entity> nearbyEntities = player.getNearbyEntities(10, 10, 10);
        List<Animals> animals = nearbyEntities.stream()
            .filter((e) -> e instanceof PolarBear | e instanceof Wolf | e instanceof Hoglin)
            .filter((e) -> !(e instanceof Tameable) || !Objects.equals(((Tameable)e).getOwnerUniqueId(), player.getUniqueId()) )
            .map((e) -> (Animals)e)
            .collect(Collectors.toList());

        // scare away animals
        animals.forEach((e) -> {

            // erase target
            if (e instanceof Wolf) {
                ((Wolf) e).setAngry(false);
            }

            // find distant location
            Vector offset = e.getLocation().toVector()
                .setY(0)
                .subtract(player.getLocation().toVector().setY(0))
                .normalize()
                .multiply(15);
            Location dest = player.getLocation().add(offset);

            // flee
            PersistentDataContainer container = e.getPersistentDataContainer();
            container.set(Whistles.getNamespacedKey("FLEE"), PersistentDataLocation.instance, dest);
            e.setTarget(null);
            e.getPathfinder().moveTo(dest, 1.3);
        });
    }
}
