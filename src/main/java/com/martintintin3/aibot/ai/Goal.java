package com.martintintin3.aibot.ai;

import com.martintintin3.aibot.enums.GoalType;
import net.minecraft.client.MinecraftClient;

public class Goal {
    public final GoalType type;
    private Boolean enabled = false;

    public Goal(GoalType type) {
        this.type = type;
    }

    public void start() {
        this.enabled = true;
    }

    public void stop() {
        this.enabled = false;
    }

    public void tick(MinecraftClient client) {

    }
}
