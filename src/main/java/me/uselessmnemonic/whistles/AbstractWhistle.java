package me.uselessmnemonic.whistles;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

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
        ItemUseHandler itemUseHandler = this::onWhistleBlow;
        addItemHandler(itemUseHandler);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    public abstract void onWhistleBlow(PlayerRightClickEvent event);
}
