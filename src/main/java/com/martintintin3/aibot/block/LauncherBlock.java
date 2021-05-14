package com.martintintin3.aibot.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LauncherBlock extends Block {
    public LauncherBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        if(entity.getType() == EntityType.PLAYER) {
            final PlayerEntity player = (PlayerEntity) entity;

            player.setVelocity(player.getVelocity().add(0f, 4f, 0f));
        }
    }
}
