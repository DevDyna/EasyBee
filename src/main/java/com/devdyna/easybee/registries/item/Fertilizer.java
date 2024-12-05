package com.devdyna.easybee.registries.item;

import java.util.List;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class Fertilizer extends Item {

    public Fertilizer(Properties prop) {
        super(prop);

    }

    @SuppressWarnings("null")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.easybee.floreal.desc"));
            tooltipComponents.add(Component.translatable("tooltip.easybee.floreal.area"));
            tooltipComponents.add(Component.translatable("tooltip.easybee.floreal.axis"));
            
            
        } else {
            tooltipComponents.add(Component.translatable("tooltip.easybee.default.shift"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
