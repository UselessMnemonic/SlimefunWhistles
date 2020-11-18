package me.uselessmnemonic.whistles.melody;

import me.uselessmnemonic.whistles.Whistles;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

/**
 * Plays a Melody using a BukkitRunnable
 */
public class MelodyRunnable extends BukkitRunnable {

    private final Melody melody;
    private int index;
    private Player player;

    /**
     * Creates the runnable for a specific Melodu
     * @param melody The Melody to play
     */
    public MelodyRunnable(@Nonnull Melody melody) {
        this.melody = melody;
    }

    /**
     * Plays the melody as emanating from a particular Player
     * @param player The Player that will play the Melody
     */
    public void play(@Nonnull Player player) {
        this.player = player;
        runTaskTimer(Whistles.getInstance(), 0, melody.getPeriod());
    }

    /**
     * Rests the playing of the Melody
     */
    public void reset() {
        index = 0;
    }

    @Override
    public void run() {
        if (index < melody.size()) {
            Location location = player.getLocation();
            World world = player.getWorld();
            Note note = melody.getNote(index);
            if (note != null) world.playSound(location, melody.getSound(), 1.0f, note.pitch);
            index++;
        }
        else this.cancel();
    }

}
