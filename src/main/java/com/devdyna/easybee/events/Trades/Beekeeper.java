package com.devdyna.easybee.events.Trades;

import com.devdyna.easybee.Utils.Calc;
import com.devdyna.easybee.registries.item.BasicItem;
import com.devdyna.easybee.registries.villager.Profession;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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
    public static void CreateTrade(VillagerTradesEvent event, int VillagerTradeLevel,
            Item ItemCost, int ItemCostCount, Item ItemSell, int ItemSellCount, int maxUses, int xp,
            double priceMultiplier) {
        event.getTrades().get(VillagerTradeLevel)
                .add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(ItemCost, ItemCostCount),
                        new ItemStack(ItemSell, ItemSellCount), maxUses, xp, (float) priceMultiplier));
    }

    /**
     * Emerald -> Item
     */
    public static void Buy(VillagerTradesEvent event, int level, Item itemToBuy, int itemCount, int emeCost) {
        CreateTrade(event, level, Items.EMERALD, emeCost, itemToBuy, itemCount, 12, 10, 0.1);
    }

    /**
     * Item -> Emerald
     */
    public static void Sell(VillagerTradesEvent event, int level, Item itemToSell, int itemCount, int emeValue) {
        CreateTrade(event, level, itemToSell, itemCount, Items.EMERALD, emeValue, 12, 10, 0.1);
    }

    // private final TagKey<Item> FLOWERS = TagKey.create(
    //         Registries.ITEM,
    //         ResourceLocation.fromNamespaceAndPath("minecraft", "flowers"));

    private final TagKey<Item> CANDLES = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("minecraft", "candles"));

    @SubscribeEvent
    public void addCustomTrades(VillagerTradesEvent event) {

        if (event.getType() == Profession.BEEKEEPER.get()) {

            // ------------------------------//
            if (Calc.rnd50()) {
                Buy(event, 1, BasicItem.BEEWAX.asItem(), Calc.rnd(1, 4), Calc.rnd(1, 4));
            } else {
                Sell(event, 1, BasicItem.BEEWAX.asItem(), 4, Calc.rnd(1, 4));
            }

            if (Calc.rnd50()) {
                Buy(event, 1, BasicItem.PATINA.asItem(), 4, Calc.rnd(1, 4));
            } else {
                Sell(event, 1, BasicItem.PATINA.asItem(), 8, Calc.rnd(1, 4));
            }
            // ------------------------------//

            // ------------------------------//
            if (Calc.rnd50()) {
                Buy(event, 2, Items.HONEYCOMB, Calc.rnd(1, 4), Calc.rnd(1, 4));
            } else {
                Sell(event, 2, Items.HONEYCOMB, Calc.rnd(4, 8), Calc.rnd(1, 4));
            }

            if (Calc.rnd50()) {
                Buy(event, 2, Items.HONEY_BOTTLE, Calc.rnd(1, 4), Calc.rnd(1, 4));
            } else {
                Sell(event, 2, Items.HONEY_BOTTLE, Calc.rnd(3, 5), Calc.rnd(1, 4));
            }
            // ------------------------------//

            // ------------------------------//

            if (Calc.rnd50()) {
                Buy(event, 3, BasicItem.FLOREAL_FERTILIZER.asItem(), Calc.rnd(1, 4), Calc.rnd(1, 4));
            } else {
                Sell(event, 3, BasicItem.FLOREAL_FERTILIZER.asItem(), Calc.rnd(3, 5), Calc.rnd(1, 4));
            }

            if (Calc.rnd50()) {
                Buy(event, 3, Items.FLOWER_POT, Calc.rnd(1, 4), Calc.rnd(1, 4));
            } else {
                Sell(event, 3, Items.FLOWER_POT, Calc.rnd(3, 5), Calc.rnd(1, 4));
            }

            // ------------------------------//

            // ------------------------------//

            Buy(event, 4, Items.FLINT_AND_STEEL, 1, Calc.rnd(1, 4));

            for (Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(CANDLES)) {

                if (Calc.rnd50()) {
                    if (Calc.rnd25()) {
                        Buy(event, 4, holder.value(), Calc.rnd(4, 12), Calc.rnd(1, 4));
                    } else {
                        Sell(event, 4, holder.value(), Calc.rnd(1, 16), Calc.rnd(1, 4));
                    }
                }
            }

            if (Calc.rnd50()) {
                Buy(event, 4, Items.CLOCK, 1, Calc.rnd(1, 4));
            } else {
                Buy(event, 4, Items.MILK_BUCKET, 1, Calc.rnd(4, 7));
            }
            // ------------------------------//

            // ------------------------------//
            Buy(event, 5, BasicItem.BEE.asItem(), 1, Calc.rnd(4, 32));
            // ------------------------------//

        }

    }
}
