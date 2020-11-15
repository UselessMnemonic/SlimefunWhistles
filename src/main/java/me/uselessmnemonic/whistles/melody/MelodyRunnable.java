package me.uselessmnemonic.whistles.melody;

import me.uselessmnemonic.whistles.Whistles;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MelodyRunnable extends BukkitRunnable {

    private final Melody melody;
    private int index;
    private Player player;

    public MelodyRunnable(Melody melody) {
        this.melody = melody;
    }

    public void play(Player player) {
        this.player = player;
        runTaskTimer(Whistles.getInstance(), 0, melody.getPeriod());
    }

    public void reset() {
        index = 0;
    }

    @Override
    public void run() {
        if (melody != null && index < melody.size()) {
            Location location = player.getLocation();
            World world = player.getWorld();
            Note note = melody.getNote(index);
            if (note != null) world.playSound(location, melody.getSound(), 1.0f, note.pitch);
            index++;
        }
        else this.cancel();
    }

}
