package com.martintintin3.aibot;

import com.martintintin3.aibot.block.LauncherBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Aibot implements ModInitializer {
    public static final Block LAUNCHER_BLOCK = new LauncherBlock(FabricBlockSettings.of(Material.STONE)
            .sounds(BlockSoundGroup.BASALT)
            .luminance(2)
            .strength(3f, 3f)
            .breakByTool(FabricToolTags.PICKAXES, 1));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("aibot", "launcher"), LAUNCHER_BLOCK);
    }
}
