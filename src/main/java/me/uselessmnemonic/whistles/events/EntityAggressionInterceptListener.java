package me.uselessmnemonic.whistles.events;

import me.uselessmnemonic.whistles.Whistles;
import me.uselessmnemonic.whistles.persistence.PersistentDataBoolean;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.persistence.PersistentDataContainer;

public class EntityAggressionInterceptListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamaged(EntityDamageByEntityEvent event) {

        // get entities
        Entity entity = event.getEntity();
        PersistentDataContainer container = entity.getPersistentDataContainer();

        // intercept aggression
        if (container.has(Whistles.getNamespacedKey("STOP_AGGRO"), PersistentDataBoolean.instance)) {
            container.remove(Whistles.getNamespacedKey("STOP_AGGRO"));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityTargetEntity(EntityTargetEvent event) {

        // get entities
        Entity entity = event.getEntity();
        PersistentDataContainer container = entity.getPersistentDataContainer();

        // intercept aggression
        if (container.has(Whistles.getNamespacedKey("STOP_AGGRO"), PersistentDataBoolean.instance)) {
            container.remove(Whistles.getNamespacedKey("STOP_AGGRO"));
            event.setTarget(null);
        }
    }

}
