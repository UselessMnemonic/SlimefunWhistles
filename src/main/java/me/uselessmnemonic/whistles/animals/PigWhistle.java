package me.uselessmnemonic.whistles.animals;

import com.destroystokyo.paper.entity.ai.MobGoals;
import com.destroystokyo.paper.entity.ai.VanillaGoal;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.uselessmnemonic.whistles.AbstractWhistle;
import me.uselessmnemonic.whistles.melody.Melody;
import me.uselessmnemonic.whistles.melody.Note;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.stream.Collectors;

public class PigWhistle extends AbstractWhistle {

    private static final Melody MELODY = new Melody(
        Instrument.FLUTE, 2,
        Note.GSh0,
        Note.C1,
        null,
        null,
        Note.D1,
        null,
        null,
        Note.DSh1,
        null, null, null, null,
        Note.C1,
        Note.DSh1,
        null,
        null,
        Note.F1,
        null,
        null,
        Note.G1
    );

    public PigWhistle() {
        super(
            new SlimefunItemStack(
                "PIG_WHISTLE",
                Material.BAMBOO,
                ChatColor.WHITE + "Pig Whistle",
                ChatColor.YELLOW + "Attracts nearby Pig-based mobs"
            ),
            new ItemStack[] {
                    new ItemStack(Material.BAMBOO), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.QUARTZ),
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

        // find all Pig-based mobs
        List<Entity> nearbyEntities = player.getNearbyEntities(20, 20, 20);
        List<Mob> pigs = nearbyEntities.stream()
                .filter((e) -> e instanceof Pig | e instanceof Hoglin | e instanceof PiglinAbstract | e instanceof Zoglin | e instanceof PigZombie)
                .map((e) -> (Mob)e)
                .collect(Collectors.toList());

        // bring mobs to player
        pigs.forEach((e) -> e.getPathfinder().moveTo(player, 1.3));
    }
}
