package com.martintintin3.aibot.ai.goals;

import com.martintintin3.aibot.ai.Goal;
import com.martintintin3.aibot.ai.Pathfinder;
import com.martintintin3.aibot.enums.GoalType;
import com.martintintin3.aibot.enums.PathinderMode;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;

public class FindBlock extends Goal {
    private final Material material;

    public FindBlock(GoalType type, MinecraftClient client, Material material) {
        super(type, client);
        this.material = material;
    }

    @Override
    public void tick(MinecraftClient client) {
        // TODO Somehow check if block is at most 20 blocks away
        if(false) {

        } else {
            Pathfinder.setMode(PathinderMode.WANDER);
            Pathfinder.enable();
        }
    }
}
