package com.devdyna.easybee.events;

import com.devdyna.easybee.Utils.Calc;
import com.devdyna.easybee.registries.item.BasicItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class FlorealEvent {

    public FlorealEvent() {
    }

    private int scaleX = 3;
    private int scaleY = 0;
    private int scaleZ = 3;

    private int maxTry = 10;

    // private TagKey<Block> FLOWERS = TagKey.create(
    // Registries.BLOCK,
    // ResourceLocation.fromNamespaceAndPath("minecraft", "small_flowers"));

    private Block[] flower_list = { Blocks.POPPY, Blocks.DANDELION, Blocks.BLUE_ORCHID, Blocks.OXEYE_DAISY,
            Blocks.AZURE_BLUET, Blocks.ALLIUM, Blocks.RED_TULIP, Blocks.PINK_TULIP, Blocks.WHITE_TULIP,
            Blocks.ORANGE_TULIP, Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY };

    private boolean isClear(Level l, BlockPos p) {
        return l.getBlockState(p.above()).is(BlockTags.AIR);
    }

    private boolean isSoil(Level l, BlockPos p) {
        return l.getBlockState(p).is(BlockTags.DIRT);
    }

    private void tryPlace(Level level, BlockPos pos) {
        if (isSoil(level, pos) && isClear(level, pos) && Calc.rnd75()) {
            level.setBlockAndUpdate(pos.above(), flower_list[Calc.rndSelector(flower_list.length)].defaultBlockState());

        }

        if (isSoil(level, pos) && isClear(level, pos) && Calc.rnd75()) {
            level.addParticle(ParticleTypes.COMPOSTER, true, pos.getX() + 0.5,
                    pos.getY() + 1.75,
                    pos.getZ() + 0.5, 0, 0, 0);
        }
    }

    @SubscribeEvent
    public void FlorealClickEvent(PlayerInteractEvent.RightClickBlock event) {

        if (!event.getItemStack().is(BasicItem.FLOREAL_FERTILIZER))
            return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        event.getEntity().swing(InteractionHand.MAIN_HAND);

        if (!event.getEntity().isCreative())
            event.getEntity().getMainHandItem().setCount(event.getEntity().getMainHandItem().getCount() - 1);

        tryPlace(level, pos);

        for (int i = 0; i < maxTry; i++) {

            BlockPos dynpos = new BlockPos(pos.getX() + Calc.rnd(-scaleX, scaleX),
                    pos.getY() + Calc.rnd(-scaleY, scaleY), pos.getZ() + Calc.rnd(-scaleZ, scaleZ));

            tryPlace(level, dynpos);

        }

        level.playLocalSound(pos.getX(), pos.getY(),
                pos.getZ(), SoundEvents.BONE_MEAL_USE, SoundSource.AMBIENT, 100,
                (int) Math.floor(Math.random() * 2), true);
    }
}
