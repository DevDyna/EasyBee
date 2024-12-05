package com.devdyna.easybee.registries.item;

import com.devdyna.easybee.EasyBee;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BasicItem {

        BasicItem() {
        }

        public void register(IEventBus bus) {
                ITEMS.register(bus);
        }

        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EasyBee.MODID);

        public static final DeferredItem<Item> BEEWAX = ITEMS.register("beewax", () -> new SimpleTipItem("beewax",
                        new Item.Properties().food(new FoodProperties.Builder()
                                        .alwaysEdible().nutrition(1).saturationModifier(2f)
                                        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 10), 50)
                                        .build())));

                                        //to replace DeferredSpawnEggItem to CustomItemSpawnEntity *TODO*
        public static final DeferredItem<Item> BEE = ITEMS.register("bee",
                        () -> new DeferredSpawnEggItem(() -> EntityType.BEE, 0x15582019, 0x4400155,
                                        new Item.Properties()));

        public static final DeferredItem<Item> SCOOP = ITEMS.register("scoop",
                        () -> new ScoopItem("scoop", new Item.Properties()));

        public static final DeferredItem<Item> FLOREAL_FERTILIZER = ITEMS.register("floreal_fertilizer",
                        () -> new Fertilizer(new Item.Properties()));

        // public static final DeferredItem<Item> MINERAL_FERTILIZER =
        // ITEMS.registerSimpleItem("mineral_fertilizer");

        public static final DeferredItem<Item> COPPER_NUGGET = ITEMS.registerSimpleItem("copper_nugget");
        public static final DeferredItem<Item> RAW_COPPER_NUGGET = ITEMS.registerSimpleItem("raw_copper_nugget");
        public static final DeferredItem<Item> PATINA = ITEMS.registerSimpleItem("patina");

}
