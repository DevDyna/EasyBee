package com.devdyna.easybee.events;

import com.devdyna.easybee.registries.item.BasicItem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class BeeSpawn {

    @SubscribeEvent
    public void itemClickEvent(PlayerInteractEvent.RightClickItem event) {
        ItemStack item = event.getItemStack();
        BlockPos pos = event.getPos();
        if (item.is(BasicItem.BEE)) {
            Bee bee = new Bee(EntityType.BEE, event.getLevel());
            bee.moveTo(pos.getX() + 0.5, pos.getY() + 0.25, pos.getZ() + 0.5, 0, 0);
            //bee.hurtTime = item.getComponents()
            event.getLevel().addFreshEntity(bee);
        }

    }

}
