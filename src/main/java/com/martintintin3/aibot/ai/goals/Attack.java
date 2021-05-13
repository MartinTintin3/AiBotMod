package com.martintintin3.aibot.ai.goals;

import com.martintintin3.aibot.ai.Goal;
import com.martintintin3.aibot.ai.Pathfinder;
import com.martintintin3.aibot.enums.GoalType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class Attack extends Goal {
    private final Entity target;
    private MinecraftClient client;

    public Attack(GoalType type, MinecraftClient client, Entity target) {
        super(type);
        this.target = target;
        this.client = client;
    }

    @Override
    public void setup() {
        if(this.client.player.distanceTo(this.target) > 2) {
            Pathfinder.enable(this.target.getBlockPos().down());
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
            this.setFinished(true);
        }
    }
}
