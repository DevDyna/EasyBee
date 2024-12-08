package com.devdyna.easybee.events;

import com.devdyna.easybee.registries.item.BasicItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ScoopInteraction {

    public ScoopInteraction() {
    }

    @SubscribeEvent
    public void playerClickEvent(PlayerInteractEvent.EntityInteractSpecific event) {
        ItemStack item = event.getItemStack();
        Entity target = event.getTarget();
        Level level = event.getLevel();
        // Player player = event.getEntity();
        // InteractionHand hand = event.getHand();

        if (target.getType() == EntityType.BEE && item.is(BasicItem.SCOOP)) {
            // create a new entityitem
            ItemEntity itementity = new ItemEntity(level, target.getX(), target.getY(),
                    target.getZ(),
                    new ItemStack(BasicItem.BEE.asItem()));
            // pickup delay default
            itementity.setDefaultPickUpDelay();
            // TODO
            //  net.minecraft.world.item.MobBucketItem;
            // bee_spawn_egg[entity_data={id:"minecraft:bee",UUID:[I;0,0,0,0],Health:0f,CustomName:'"
            // "'}]
            // spawn entity
            level.addFreshEntity(itementity);
            item.setDamageValue(1);
            level.addParticle(ParticleTypes.ITEM_COBWEB, true, target.getX(),
                    target.getY(),
                    target.getZ(), 0, 0, 0);
            level.playLocalSound(target.getX(), target.getY(),
                    target.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.AMBIENT, 10,
                    (int) Math.floor(Math.random() * 2), true);
            target.remove(RemovalReason.KILLED);
        }

    }
}
