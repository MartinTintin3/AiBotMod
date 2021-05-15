package com.martintintin3.aibot.ai;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

public class GoalManager {
    private static ArrayList<BrainGoal> brainGoalQueue = new ArrayList<BrainGoal>();

    public static void addBrainGoal(BrainGoal brainGoal) {

    }

    public static void removeBrainGoal(BrainGoal brainGoal) {

    }

    public static Boolean cointainsBrainGoal(BrainGoal brainGoal) {
        return brainGoalQueue.contains(brainGoal);
    }

    public static void tick(MinecraftClient client) {
        if(brainGoalQueue.stream().anyMatch(brainGoal -> !brainGoal.isPaused())) {
            brainGoalQueue.stream().filter(brainGoal -> !brainGoal.isPaused()).findFirst().get().tick(client);
        }
    }
}
