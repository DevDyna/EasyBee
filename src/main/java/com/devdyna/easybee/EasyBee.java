package com.devdyna.easybee;

import java.util.List;

import org.slf4j.Logger;

import com.google.common.collect.ImmutableSet;
import com.mojang.logging.LogUtils;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(EasyBee.MODID)
public class EasyBee {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "easybee";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // Create a Deferred Register to hold Items which will all be registered under
    // the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be
    // registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and
    // saturation 2
    public static final DeferredItem<Item> BEEWAX = ITEMS.registerSimpleItem("beewax",
            new Item.Properties().food(new FoodProperties.Builder()
                    .alwaysEdible().nutrition(1).saturationModifier(2f)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 10), 50).build()));

    public static final DeferredItem<Item> BEE = ITEMS.register("bee",
            () -> new DeferredSpawnEggItem(() -> EntityType.BEE, 0x15582019, 0x4400155, new Item.Properties()));

    public static final DeferredItem<Item> SCOOP = ITEMS.registerSimpleItem("scoop",
            new Item.Properties().durability(128));

    // Creates a creative tab with the id "examplemod:example_tab" for the example
    // item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
            .register("easybee", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.easybee")) // The language key for the title of your
                                                                  // CreativeModeTab
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> BEEWAX.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(BEEWAX.get());
                        output.accept(SCOOP.get());
                        output.accept(BEE.get());

                    }).build());

    public static final DeferredRegister<VillagerProfession> VILLAGERS = DeferredRegister
            .create(BuiltInRegistries.VILLAGER_PROFESSION, EasyBee.MODID);
    public static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister
            .create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, EasyBee.MODID);

    public static final DeferredHolder<PoiType, PoiType> BEEKEEPER_POI = POINTS_OF_INTEREST.register("beekeeper_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.HONEY_BLOCK.getStateDefinition().getPossibleStates()), 1,
                    1));
    public static final DeferredHolder<VillagerProfession, VillagerProfession> BEEKEEPER = VILLAGERS.register(
            "beekeeper",
            () -> new VillagerProfession("beekeeper", (b) -> b.value() == BEEKEEPER_POI.get(),
                    (b) -> b.value() == BEEKEEPER_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.BEEHIVE_WORK));

    public EasyBee(IEventBus modEventBus, ModContainer modContainer) {
        VILLAGERS.register(modEventBus);
        POINTS_OF_INTEREST.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void registerBrewingRecipes(final RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, BEEWAX.get(), Potions.HEALING);
    }

    @SubscribeEvent
    public void scoopClickEvent(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getTarget().getType() == EntityType.BEE && event.getItemStack().is(SCOOP)) {
            ItemEntity itementity = new ItemEntity(event.getLevel(), event.getTarget().getX(), event.getTarget().getY(),
                    event.getTarget().getZ(),
                    new ItemStack(BEE.asItem()));
            itementity.setDefaultPickUpDelay();
            event.getEntity().swing(InteractionHand.MAIN_HAND);
            event.getLevel().addFreshEntity(itementity);
            event.getLevel().addParticle(ParticleTypes.ITEM_COBWEB, true, event.getTarget().getX(),
                    event.getTarget().getY(),
                    event.getTarget().getZ(), 0, 0, 0);
            event.getLevel().playLocalSound(event.getTarget().getX(), event.getTarget().getY(),
                    event.getTarget().getZ(), SoundEvents.OMINOUS_BOTTLE_DISPOSE, SoundSource.AMBIENT, 10,
                    (int) Math.floor(Math.random() * 2), true);
            event.getTarget().remove(RemovalReason.KILLED);
        }

    }

    /**
     * Allow to create a trade with any villager
     * 
     * @param VillagerTradeLevel level 1 to 5 inclusive
     */
    @SuppressWarnings("deprecation")
    public static void CreateTrade(Int2ObjectMap<List<VillagerTrades.ItemListing>> event, Integer VillagerTradeLevel,
            Item ItemCost, Integer ItemCostCount, Item ItemSell, Integer ItemSellCount, Integer maxUses, Integer xp,
            Integer priceMultiply) {
        event.get(VillagerTradeLevel)
                .add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(ItemCost, ItemCostCount),
                        new ItemStack(ItemSell, ItemSellCount), maxUses, xp, priceMultiply));
    }

    @SubscribeEvent
    public void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == BEEKEEPER.get()) {
            // Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = ;
            CreateTrade(event.getTrades(), 1, Items.HONEYCOMB, 4, Items.EMERALD, 1, 12, 1, 1);
        }

    }

    @SubscribeEvent
    public void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades
                .add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                        new ItemStack(Items.HONEYCOMB, 2), 12, 1, 0));

        rareTrades
                .add((pTrader, pRandom) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                        new ItemStack(Items.HONEY_BOTTLE, 2), 12, 1, 0));

    }

}
