package com.martintintin3.aibot.ai.goals;

import com.martintintin3.aibot.ai.Goal;
import com.martintintin3.aibot.ai.GoalManager;
import com.martintintin3.aibot.ai.Pathfinder;
import com.martintintin3.aibot.enums.GoalType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class Attack extends Goal {
    private final Entity target;

    public Attack(GoalType type, MinecraftClient client, Entity target) {
        super(type, client);
        this.target = target;
    }

    @Override
    public void setup() {
        if(this.client.player.distanceTo(this.target) > 2) {
            Pathfinder.enable();
        }
    }

    public void tick(MinecraftClient client) {
        if(client.player.distanceTo(this.target) > 2.5) {
            Integer distance = 0;
            while(this.client.world.isAir(this.target.getBlockPos().down(distance))) {
                distance++;
            }
            Pathfinder.setTargetBlock(this.target.getBlockPos().down(distance));
        } else {
            client.player.attack(this.target);
            GoalManager.removeGoal(this);
        }
    }
}
