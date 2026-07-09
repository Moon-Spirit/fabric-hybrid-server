package io.moonspirit.hybrid.bukkit.impl.inventory;

import net.minecraft.world.Container;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

public class CraftInventoryMerchant extends CraftInventory implements MerchantInventory {

    public CraftInventoryMerchant(Container container) {
        super(container);
    }

    @Override
    public int getSelectedRecipeIndex() { return 0; }

    @Override
    public MerchantRecipe getSelectedRecipe() { return null; }

    @Override
    public Merchant getMerchant() { return null; }
}
