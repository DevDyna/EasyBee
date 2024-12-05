package com.devdyna.easybee.registries.item;

import java.util.List;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class SimpleTipItem extends Item {
    private String traslationName;

    public SimpleTipItem(String traslationName, Properties prop) {
        super(prop);
        this.traslationName = traslationName;
    }

    @SuppressWarnings("null")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.easybee." + traslationName));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.easybee.default.shift"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

}
