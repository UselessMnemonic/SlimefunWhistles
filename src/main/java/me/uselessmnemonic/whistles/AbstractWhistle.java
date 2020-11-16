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

public abstract class AbstractWhistle extends SlimefunItem implements DamageableItem {

    public static Category getWhistleCategory() {
        NamespacedKey customId = new NamespacedKey(Whistles.getInstance(), "category_whistles");
        CustomItem customItem = new CustomItem(Material.BAMBOO, ChatColor.WHITE + "Magic Whistles");
        return new Category(customId, customItem);
    }

    public AbstractWhistle(SlimefunItemStack item, ItemStack[] recipe) {
        super(AbstractWhistle.getWhistleCategory(), item, RecipeType.MAGIC_WORKBENCH, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::preWhistleBlow;
        addItemHandler(itemUseHandler);
    }

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

    public void preWhistleBlow(PlayerRightClickEvent event) {
        event.cancel();
        MelodyRunnable melodyRunnable = new MelodyRunnable(getMelody());
        melodyRunnable.play(event.getPlayer());
        onWhistleBlow(event);
    }

    public abstract Melody getMelody();

    public abstract void onWhistleBlow(PlayerRightClickEvent event);
}
