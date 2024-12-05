package com.devdyna.easybee.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.devdyna.easybee.EasyBee;
import com.devdyna.easybee.registries.item.BasicItem;

public class CreativeTab {

    CreativeTab() {
    }

    public void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, EasyBee.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
            .register("easybee", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.easybee")) // The language key for the title of your
                                                                  // CreativeModeTab
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> BasicItem.BEEWAX.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(BasicItem.BEEWAX.get());
                        output.accept(BasicItem.SCOOP.get());
                        output.accept(BasicItem.BEE.get());

                    }).build());
}