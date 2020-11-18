package me.uselessmnemonic.whistles.events;

import me.uselessmnemonic.whistles.Whistles;
import me.uselessmnemonic.whistles.persistence.PersistentDataBoolean;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.persistence.PersistentDataContainer;

/**
 * This Listener mediates the aggression response, at least in Dogs
 */
public class EntityAggressionInterceptListener implements Listener {

    /*
     * This handler removes the control tag from the entity when attacked
     */
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

    /*
     * This handler immediately clears an entity's target and then
     * cleaers the control tag from the entity.
     */
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
