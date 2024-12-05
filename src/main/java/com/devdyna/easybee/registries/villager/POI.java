package com.devdyna.easybee.registries.villager;

import com.devdyna.easybee.EasyBee;
import com.google.common.collect.ImmutableSet;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class POI {
        public static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister
                        .create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, EasyBee.MODID);

        public static final DeferredHolder<PoiType, PoiType> BEEKEEPER_POI = POINTS_OF_INTEREST.register(
                        "beekeeper_poi",
                        () -> new PoiType(
                                        ImmutableSet.copyOf(
                                                        Blocks.HONEY_BLOCK.getStateDefinition().getPossibleStates()),
                                        1,
                                        1));
}
