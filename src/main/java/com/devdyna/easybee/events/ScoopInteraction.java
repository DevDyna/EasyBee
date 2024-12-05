package com.devdyna.easybee.events;

import com.devdyna.easybee.registries.item.BasicItem;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ScoopInteraction {


    public ScoopInteraction(){}

    @SubscribeEvent
    public void playerClickEvent(PlayerInteractEvent.EntityInteractSpecific event) {
        ItemStack item = event.getItemStack();

        if (event.getTarget().getType() == EntityType.BEE && item.is(BasicItem.SCOOP)) {
            ItemEntity itementity = new ItemEntity(event.getLevel(), event.getTarget().getX(), event.getTarget().getY(),
                    event.getTarget().getZ(),
                    new ItemStack(BasicItem.BEE.asItem()));
            itementity.setDefaultPickUpDelay();
            event.getEntity().swing(InteractionHand.MAIN_HAND);
            event.getLevel().addFreshEntity(itementity);
            item.setDamageValue(1);
            event.getLevel().addParticle(ParticleTypes.ITEM_COBWEB, true, event.getTarget().getX(),
                    event.getTarget().getY(),
                    event.getTarget().getZ(), 0, 0, 0);
            event.getLevel().playLocalSound(event.getTarget().getX(), event.getTarget().getY(),
                    event.getTarget().getZ(), SoundEvents.OMINOUS_BOTTLE_DISPOSE, SoundSource.AMBIENT, 10,
                    (int) Math.floor(Math.random() * 2), true);
            event.getTarget().remove(RemovalReason.KILLED);
        }

    }
}
