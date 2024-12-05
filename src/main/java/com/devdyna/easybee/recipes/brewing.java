package com.devdyna.easybee.recipes;

import com.devdyna.easybee.registries.item.BasicItem;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public class brewing {
        @SubscribeEvent
    public void registerBrewingRecipes(final RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, BasicItem.BEEWAX.get(), Potions.HEALING);
    }
}
