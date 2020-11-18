package me.uselessmnemonic.whistles.animals;

import com.destroystokyo.paper.entity.ai.MobGoals;
import com.destroystokyo.paper.entity.ai.VanillaGoal;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import me.uselessmnemonic.whistles.Whistles;
import me.uselessmnemonic.whistles.melody.Melody;
import me.uselessmnemonic.whistles.melody.Note;
import me.uselessmnemonic.whistles.persistence.PersistentDataBoolean;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SitWhistle extends AbstractWhistle {

    private static final Melody MELODY = new Melody(
        Instrument.BASS_GUITAR, 2,
        Note.ASh1,
        Note.C2,
        Note.G1,
        null,
        Note.ASh1,
        null,
        Note.C2,
        null,
        Note.DSh2,
        null,
        null,
        Note.G1
    );

    public SitWhistle() {
        super(
            new SlimefunItemStack(
                "SIT_WHISTLE",
                Material.BAMBOO,
                ChatColor.WHITE + "Sit Whistle",
                ChatColor.YELLOW + "Makes the player's pets sit and stand."
            ),
            new ItemStack[] {
                new ItemStack(Material.BAMBOO), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.GOLD_NUGGET),
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

        // find all pets in the immediate area
        List<Entity> nearbyEntities = player.getNearbyEntities(15, 15, 15);
        List<Sittable> pets = nearbyEntities.stream()
                .filter((e) -> e instanceof Tameable && e instanceof Sittable)
                .map((e) -> (Sittable & Tameable)e)
                .filter((e) -> e.isTamed() && Objects.equals(e.getOwnerUniqueId(), player.getUniqueId()))
                .collect(Collectors.toList());

        // count how many pets are sitting
        long numPets = pets.size();
        long numSitting = pets.stream().filter(Sittable::isSitting).count();

        // if all pets are sitting, stand them all
        if (numSitting == numPets) pets.forEach((e) -> e.setSitting(false));

        // if all pets are standing, sit them all
        else if (numSitting == 0) pets.forEach((e) -> {
            e.setSitting(true);
            ((Mob)e).setTarget(null);
            PersistentDataContainer container = ((Mob)e).getPersistentDataContainer();
            container.set(Whistles.getNamespacedKey("STOP_AGGRO"), PersistentDataBoolean.instance, true);
        });

        // if most pets are sitting, sit the rest
        else if (numSitting > numPets / 2) pets.forEach((e) -> {
            e.setSitting(true);
            ((Mob)e).setTarget(null);
            PersistentDataContainer container = ((Mob)e).getPersistentDataContainer();
            container.set(Whistles.getNamespacedKey("STOP_AGGRO"), PersistentDataBoolean.instance, true);
        });

        // if most pets are standing, stand the rest
        else pets.forEach((e) -> e.setSitting(false));
    }
}
