package com.devdyna.easybee.registries.villager;

import com.devdyna.easybee.EasyBee;
import com.google.common.collect.ImmutableSet;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Profession {
    public static final DeferredRegister<VillagerProfession> VILLAGERS = DeferredRegister
            .create(BuiltInRegistries.VILLAGER_PROFESSION, EasyBee.MODID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> BEEKEEPER = VILLAGERS.register(
            "beekeeper",
            () -> new VillagerProfession("beekeeper", (b) -> b.value() == POI.BEEKEEPER_POI.get(),
                    (b) -> b.value() == POI.BEEKEEPER_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.BEEHIVE_WORK));

}
