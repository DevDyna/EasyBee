package com.devdyna.easybee.events.Trades;

import java.util.List;

import com.devdyna.easybee.registries.villager.Profession;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public class Beekeeper {
        /**
     * Allow to create a trade with any villager
     * 
     * @param VillagerTradeLevel level 1 to 5 inclusive
     */
    public static void CreateTrade(Int2ObjectMap<List<VillagerTrades.ItemListing>> event, int VillagerTradeLevel,
            Item ItemCost, int ItemCostCount, Item ItemSell, int ItemSellCount, int maxUses, int xp,
            int priceMultiplier) {
        event.get(VillagerTradeLevel)
                .add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(ItemCost, ItemCostCount),
                        new ItemStack(ItemSell, ItemSellCount), maxUses, xp, priceMultiplier));
    }

    @SubscribeEvent
    public void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == Profession.BEEKEEPER.get()) {
            CreateTrade(event.getTrades(), 1, Items.HONEYCOMB, 4, Items.EMERALD, 1, 12, 1, 1);
        }

    }
}
