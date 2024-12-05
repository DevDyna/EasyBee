package com.devdyna.easybee;

import org.slf4j.Logger;

import com.devdyna.easybee.events.ScoopInteraction;
import com.devdyna.easybee.registries.CreativeTab;
import com.devdyna.easybee.registries.item.BasicItem;
import com.devdyna.easybee.registries.villager.POI;
import com.devdyna.easybee.registries.villager.Profession;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(EasyBee.MODID)
public class EasyBee {
        public static final String MODID = "easybee";

        public EasyBee(IEventBus modEventBus, ModContainer modContainer) {
                Profession.VILLAGERS.register(modEventBus);
                POI.POINTS_OF_INTEREST.register(modEventBus);
                BasicItem.ITEMS.register(modEventBus);
                CreativeTab.CREATIVE_MODE_TABS.register(modEventBus);
                NeoForge.EVENT_BUS.register(this);
                NeoForge.EVENT_BUS.register(new ScoopInteraction());
                // TODO
                // NeoForge.EVENT_BUS.register(new BeeSpawn());
                // NeoForge.EVENT_BUS.register(new FlorealEvent());
        }

        // demo event
        private static final Logger LOGGER = LogUtils.getLogger();

        @SubscribeEvent
        public void onServerStarting(ServerStartingEvent event) {
                LOGGER.info("*Bees are looking you*");
        }

}
