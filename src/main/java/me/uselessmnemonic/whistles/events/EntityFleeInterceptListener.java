package me.uselessmnemonic.whistles.events;

import me.uselessmnemonic.whistles.Whistles;
import me.uselessmnemonic.whistles.persistence.PersistentDataLocation;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.util.Vector;

public class EntityFleeInterceptListener implements Listener {

    @EventHandler(priority =  EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        // get flee target
        PersistentDataContainer container = event.getEntity().getPersistentDataContainer();

        // if attacked while fleeing, return to normal behavior
        if (container.has(Whistles.getNamespacedKey("FLEE"), PersistentDataLocation.instance)) {
            container.remove(Whistles.getNamespacedKey("FLEE"));
        }
    }

    @EventHandler
    public void onTame(EntityTameEvent event) {

        // remove tag
        Entity entity = event.getEntity();
        PersistentDataContainer container = entity.getPersistentDataContainer();
        if (container.has(Whistles.getNamespacedKey("FLEE"), PersistentDataLocation.instance)) {
            container.remove(Whistles.getNamespacedKey("FLEE"));
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityTargetEntity(EntityTargetEvent event) {

        // get entity
        Entity entity = event.getEntity();

        // get flee target
        PersistentDataContainer container = entity.getPersistentDataContainer();
        if (container.has(Whistles.getNamespacedKey("FLEE"), PersistentDataLocation.instance)) {

            EntityTargetEvent.TargetReason reason = event.getReason();
            Location dest = container.get(Whistles.getNamespacedKey("FLEE"), PersistentDataLocation.instance);

            if (reason == EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY ||
                    reason == EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY ||
                    !(event.getTarget() instanceof Player)) {
                container.remove(Whistles.getNamespacedKey("FLEE"));
            }

            else if (dest != null) {
                Vector currCoord = entity.getLocation().toVector().setY(0);
                Vector destCoord = dest.toVector();

                // still too far away from target, keep running
                if (currCoord.distance(destCoord) > 0.5) {
                    if (entity instanceof Mob) {
                        dest.setY(currCoord.getY());
                        ((Mob)entity).getPathfinder().moveTo(dest, 1.3);
                    }
                    event.setTarget(null);
                }

                // we have fled, so return to normal behavior
                else container.remove(Whistles.getNamespacedKey("FLEE"));
            }
        }
    }

}
