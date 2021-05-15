package com.martintintin3.aibot.ai;

import com.martintintin3.aibot.enums.GoalType;
import net.minecraft.client.MinecraftClient;

public class Goal {
    public final GoalType type;
    private Boolean paused = false;
    protected MinecraftClient client;

    public Goal(GoalType type, MinecraftClient client) {
        this.type = type;
        this.client = client;
    }

    public void setup() {};

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
    }

    public Boolean isPaused() {
        return this.paused;
    }

    public void delete() {
        GoalManager.removeGoal(this);
    }

    public void tick(MinecraftClient client) { };
}
