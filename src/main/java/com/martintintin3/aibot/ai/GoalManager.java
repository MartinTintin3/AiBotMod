package com.martintintin3.aibot.ai;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

public class GoalManager {
    private static ArrayList<Goal> goalQueue = new ArrayList<Goal>();

    public static void addGoal(Goal goal) {

    }

    public static void removeGoal(Goal goal) {

    }

    public static Boolean cointainsGoal(Goal goal) {
        return goalQueue.contains(goal);
    }

    public static void tick(MinecraftClient client) {
        if(goalQueue.stream().anyMatch(goal -> !goal.isPaused())) {
            goalQueue.stream().filter(goal -> !goal.isPaused()).findFirst().get().tick(client);
        }
    }
}
