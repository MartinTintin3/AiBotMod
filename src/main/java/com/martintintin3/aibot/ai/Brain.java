package com.martintintin3.aibot.ai;

import net.minecraft.client.MinecraftClient;

public class Brain {
    private static Boolean enabled = false;
    private static MinecraftClient client;
    private static Integer jumpAmount = 0;

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static Boolean isEnabled() {
        return enabled;
    }

    public static void tick(MinecraftClient client) {
        Brain.client = client;

        if(enabled) {
            disableMovementKeys();

            // Check if jump is needed
            if(jumpAmount > 0 && client.player.getVelocity().y > -0.079f && client.player.getVelocity().y <= -0.078f) {
                client.options.keyJump.setPressed(true);
                jumpAmount--;
            }

            GoalManager.tick(client);
            Pathfinder.tick(client);
        }
    }

    private static void jump() {
        jumpAmount++;
    }

    private static void disableMovementKeys() {
        client.options.keyForward.setPressed(false);
        client.options.keyBack.setPressed(false);
        client.options.keyLeft.setPressed(false);
        client.options.keyRight.setPressed(false);
        client.options.keySneak.setPressed(false);
        client.options.keyJump.setPressed(false);
    }
}
