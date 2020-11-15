package me.uselessmnemonic.whistles;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.uselessmnemonic.whistles.pets.SitWhistle;
import org.bukkit.plugin.java.JavaPlugin;

public class Whistles extends JavaPlugin implements SlimefunAddon {

    private static Whistles instance;

    public static Whistles getInstance() {
        return instance;
    }

    public Whistles() {
        instance = this;
    }

    @Override
    public void onEnable() {
        SitWhistle sitWhistle = new SitWhistle();
        sitWhistle.register(this);
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
