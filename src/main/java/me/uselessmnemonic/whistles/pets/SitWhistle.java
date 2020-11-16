package me.uselessmnemonic.whistles.pets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import me.uselessmnemonic.whistles.melody.Melody;
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
import java.util.stream.Collectors;

public class SitWhistle extends AbstractWhistle {

    private static final Melody MELODY = new Melody(
        Instrument.FLUTE, 2,
        Note.D0,
        Note.B0,
        Note.G0
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
    public Melody getMelody() {
        return MELODY;
    }

    @Override
    public void onWhistleBlow(PlayerRightClickEvent event) {
        Player player = event.getPlayer();

        // find all pets in the immediate area
        List<Entity> nearbyEntities = player.getNearbyEntities(10, 10, 10);
        List<Sittable> pets = nearbyEntities.stream()
                .filter((e) -> e instanceof Tameable && e instanceof Sittable)
                .map((e) -> (Sittable & Tameable)e)
                .filter((e) -> e.isTamed() && Objects.equals(e.getOwner(), player))
                .collect(Collectors.toList());

        // count how many pets are sitting
        long numPets = pets.size();
        long numSitting = pets.stream().filter(Sittable::isSitting).count();

        // if all pets are sitting, stand them all
        if (numSitting == numPets) pets.forEach((e) -> e.setSitting(false));

        // if all pets are standing, sit them all
        else if (numSitting == 0) pets.forEach((e) -> e.setSitting(true));

        // if most pets are sitting, sit the rest
        else if (numSitting > numPets / 2) pets.forEach((e) -> e.setSitting(true));

        // if most pets are standing, stand the rest
        else pets.forEach((e) -> e.setSitting(false));
    }
}
