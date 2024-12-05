package com.devdyna.easybee.events.Trades;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

public class WanderingTrader {

    public void CreateTrade(WandererTradesEvent event, boolean isRare, Item ItemCost, int ItemCostCount, Item ItemSell,
            int ItemSellCount, int maxUses, int xp, float priceMultiplier) {
        MerchantOffer trade = new MerchantOffer(new ItemCost(ItemCost, ItemCostCount),
                new ItemStack(ItemSell, ItemSellCount), maxUses, xp, priceMultiplier);
        if (isRare) {
            event.getRareTrades().add((pTrader, pRandom) -> trade);
        } else {
            event.getGenericTrades().add((pTrader, pRandom) -> trade);
        }
    }

    @SubscribeEvent
    public void addCustomWanderingTrades(WandererTradesEvent event) {

        CreateTrade(event, false, Items.EMERALD, 1, Items.HONEYCOMB, 3, 12, 1, 0);

        CreateTrade(event, true, Items.EMERALD, 1, Items.HONEY_BOTTLE, 4, 12, 1, 0);

    }
}
