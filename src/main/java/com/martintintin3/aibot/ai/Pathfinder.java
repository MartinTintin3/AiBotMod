package com.martintintin3.aibot.ai;

import com.martintintin3.aibot.enums.PathinderMode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class Pathfinder {
    private static Boolean enabled = false;
    private static BlockPos targetBlock;
    private static MinecraftClient client;
    private static PathinderMode mode;

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static Boolean isEnabled() {
        return enabled;
    }

    public static void setTargetBlock(BlockPos newTargetBlock) {
        targetBlock = newTargetBlock;
    }

    public static void setMode(PathinderMode mode) {
        Pathfinder.mode = mode;
    }

    public static void tick(MinecraftClient client) {
        Pathfinder.client = client;

        if(enabled) {
            switch (mode) {
                case WANDER:
                    // TODO Implement Pathfinder's WANDER mode
                case FIND_BLOCK:
                    // TODO Implement Pathinder's FIND_BLOCK mode
                default:
                    mode = PathinderMode.NONE;
            }
        }
    }
}
