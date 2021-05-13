package com.martintintin3.aibot;

import com.martintintin3.aibot.ai.Brain;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class AibotClient implements ClientModInitializer {
    public static KeyBinding startKeybind;
    public static KeyBinding stopKeybind;

    @Override
    public void onInitializeClient() {
        startKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aibot.start",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                "category.aibot.aibot"
        ));
        stopKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aibot.stop",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DOWN,
                "category.aibot.aibot"
        ));

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(startKeybind.isPressed() && !Brain.isEnabled()) {
                Brain.enable();
            } else if(stopKeybind.isPressed() && Brain.isEnabled()) {
                Brain.disable();
            }

            Brain.tick(client);
        });
    }
}
