package com.martintintin3.aibot.ai;

import com.martintintin3.aibot.enums.GoalType;
import net.minecraft.client.MinecraftClient;

public class Goal {
    public final GoalType type;
    private Boolean finished = false;

    public Goal(GoalType type) {
        this.type = type;
    }

    public void setup() {};

    public Boolean isFinished() {
        return this.finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void tick(MinecraftClient client) { }
}
