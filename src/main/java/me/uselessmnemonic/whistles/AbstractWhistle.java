package me.uselessmnemonic.whistles;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.uselessmnemonic.whistles.melody.Melody;
import me.uselessmnemonic.whistles.melody.MelodyRunnable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * The root class of all Whistles
 */
public abstract class AbstractWhistle extends SlimefunItem implements DamageableItem {

    private static Category categoryInstance;

    /**
     * Returns a singleton instance of the Category in which all Whistles belong
     * @return The custom Whistle Category
     */
    @Nonnull
    public static Category getWhistleCategory() {
        if (categoryInstance == null) {
            NamespacedKey customId = new NamespacedKey(Whistles.getInstance(), "category_whistles");
            CustomItem customItem = new CustomItem(Material.BAMBOO, ChatColor.WHITE + "Magic Whistles");
            categoryInstance = new Category(customId, customItem);
        }
        return categoryInstance;
    }

    /**
     * The Constructor for the AbstractWhistle
     * @param item The SlimefunItemStack that defines the appearance of the item
     * @param recipe The recipe for crafting the item
     */
    public AbstractWhistle(SlimefunItemStack item, ItemStack[] recipe) {
        super(AbstractWhistle.getWhistleCategory(), item, RecipeType.MAGIC_WORKBENCH, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::preWhistleBlow;
        addItemHandler(itemUseHandler);
    }

    /**
     * Retrieves an ItemStack for use as a template, modified by adding a UUID
     * to distinguish one Whistle from the next
     * @return The ItemStack template
     */
    @Nonnull
    @Override
    public ItemStack getItem() {
        ItemStack stack = new ItemStack(super.getItem());
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) return stack;
        PersistentDataContainer container = meta.getPersistentDataContainer();

        container.set(Whistles.getNamespacedKey("uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());

        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public boolean isDamageable() {
         return false;
    }

    /**
     * Called when a Whistle is used, to play a melody and to call
     * the Whistle-specific handler
     */
    public void preWhistleBlow(PlayerRightClickEvent event) {
        event.cancel();
        MelodyRunnable melodyRunnable = new MelodyRunnable(getMelody(event.getItem()));
        melodyRunnable.play(event.getPlayer());
        onWhistleBlow(event);
    }

    /**
     * Retrieves the Melody to be played when the given Whistle is used.
     * @param stack The particular Whistle used by a Player
     * @return A Melody to be played when the Whistle is used
     */
    public abstract Melody getMelody(ItemStack stack);

    /**
     * A Whistle-specific use handler.
     */
    public abstract void onWhistleBlow(PlayerRightClickEvent event);
}
