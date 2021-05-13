package com.martintintin3.aibot.ai;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class Pathfinder {
    private static Boolean enabled = false;
    private static BlockPos targetBlock;
    private static MinecraftClient client;

    public static void enable(BlockPos targetBlock) {
        Pathfinder.targetBlock = targetBlock;
        enabled = true;
    }

    public static void disable() {
        targetBlock = null;
        enabled = false;
    }

    public static void setTargetBlock(BlockPos newTargetBlock) {
        targetBlock = newTargetBlock;
    }

    public static void tick(MinecraftClient client) {
        Pathfinder.client = client;

        if(enabled) {
            // TODO Implement pathfinder
        }
    }
}
