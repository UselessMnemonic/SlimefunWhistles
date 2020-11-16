package me.uselessmnemonic.whistles;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.uselessmnemonic.whistles.pets.ScareWhistle;
import me.uselessmnemonic.whistles.pets.SitWhistle;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public class Whistles extends JavaPlugin implements SlimefunAddon {

    private static Whistles instance;

    public static Whistles getInstance() {
        return instance;
    }

    public Whistles() {
        instance = this;
    }

    public static NamespacedKey getNamespacedKey(String key) {
        return new NamespacedKey(instance, key);
    }

    @Override
    public void onEnable() {
        SitWhistle sitWhistle = new SitWhistle();
        sitWhistle.register(this);

        ScareWhistle scareWhistle = new ScareWhistle();
        scareWhistle.register(this);
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/UselessMnemonic/SlimefunWhistles/issues";
    }
}
