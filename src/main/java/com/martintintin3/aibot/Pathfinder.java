package com.martintintin3.aibot;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Pathfinder {
    private static Boolean enabled = false;
    private static MinecraftClient client;
    private static Vec3d startPosition;
    private static Direction direction;
    private static float lookDirection;
    private static Boolean didCheck = false;
    private static enum AiTypes {
        ATTACK,
        ESCAPE,
        EXPLORE,
        NONE
    }
    private static AiTypes aiType = AiTypes.NONE;
    public static Entity target = null;

    public static void start() {
        direction = client.player.getMovementDirection();
        client.player.sendMessage(new LiteralText("starting"), false);
        enabled = true;
        client.options.keyForward.setPressed(true);
        aiType = AiTypes.EXPLORE;
    }

    public static void stop() {
        client.player.sendMessage(new LiteralText("stoping"), false);
        client.options.keyForward.setPressed(false);
        client.options.keyLeft.setPressed(false);
        client.options.keyRight.setPressed(false);
        client.options.keyBack.setPressed(false);
        client.options.keySneak.setPressed(false);
        didCheck = false;
        enabled = false;
        aiType = AiTypes.NONE;
    }

    public static void tick(MinecraftClient client) {
        if(client.player == null) return;
        Pathfinder.client = client;
        if(!AibotClient.startKeybind.isPressed() || !AibotClient.stopKeybind.isPressed()) {
            if(AibotClient.startKeybind.isPressed() && !enabled) {
                start();
            }
            if(AibotClient.stopKeybind.isPressed() && enabled) {
                stop();
            }
        }
        if(enabled) {
            client.options.keyJump.setPressed(false);

            Vec3d playerPosition = client.player.getPos();

            if(didCheck == null || !didCheck) {
                client.options.keyLeft.setPressed(false);
                client.options.keyRight.setPressed(false);
                client.options.keyForward.setPressed(false);
                client.options.keyBack.setPressed(false);
                client.options.keySneak.setPressed(true);

                client.player.updatePositionAndAngles(client.player.getX(), client.player.getY(), client.player.getZ(), 180f, client.player.getPitch(client.getTickDelta()));
                if(Math.floor(playerPosition.x) + 0.4 < playerPosition.x && Math.floor(playerPosition.x) + 0.6 > playerPosition.x && Math.floor(playerPosition.z) + 0.4 < playerPosition.z && Math.floor(playerPosition.z) + 0.6 > playerPosition.z) {
                    didCheck = true;
                    startPosition = client.player.getPos();
                } else {
                    if(Math.floor(playerPosition.x) + 0.6 < playerPosition.x) {
                        client.options.keyLeft.setPressed(true);
                    } else if(Math.floor(playerPosition.x) + 0.4 > playerPosition.x) {
                        client.options.keyRight.setPressed(true);
                    }
                    if(Math.floor(playerPosition.z) + 0.6 < playerPosition.z) {
                        client.options.keyForward.setPressed(true);
                    } else if(Math.floor(playerPosition.z) + 0.4 > playerPosition.z) {
                        client.options.keyBack.setPressed(true);
                    }
                }
            } else {
                switch (aiType) {
                    default:
                        aiType = AiTypes.NONE;
                        break;
                    case EXPLORE:
                        switch (direction.asString()) {
                            case "north":
                                lookDirection = 180f;
                                if(!client.world.isAir(client.player.getBlockPos().north().up()) || (!client.world.isAir(client.player.getBlockPos().north()) && !client.world.isAir(client.player.getBlockPos().north().up(2)))) {
                                    direction = Direction.WEST;
                                    lookDirection = 90f;
                                } else if(!client.world.isAir(client.player.getBlockPos().north())) {
                                    client.options.keyJump.setPressed(true);
                                }
                                break;
                            case "south":
                                lookDirection = 0f;
                                if(!client.world.isAir(client.player.getBlockPos().south().up()) || (!client.world.isAir(client.player.getBlockPos().south()) && !client.world.isAir(client.player.getBlockPos().south().up(2)))) {
                                    direction = Direction.EAST;
                                    lookDirection = 270f;
                                } else if(!client.world.isAir(client.player.getBlockPos().south())) {
                                    client.options.keyJump.setPressed(true);
                                }
                                break;
                            case "east":
                                lookDirection = 270f;
                                if(!client.world.isAir(client.player.getBlockPos().east().up()) || (!client.world.isAir(client.player.getBlockPos().east()) && !client.world.isAir(client.player.getBlockPos().east().up(2)))) {
                                    direction = Direction.NORTH;
                                    lookDirection = 180f;
                                } else if(!client.world.isAir(client.player.getBlockPos().east())) {
                                    client.options.keyJump.setPressed(true);
                                }
                                break;
                            case "west":
                                lookDirection = 90f;
                                if(!client.world.isAir(client.player.getBlockPos().west().up()) || (!client.world.isAir(client.player.getBlockPos().west()) && !client.world.isAir(client.player.getBlockPos().west().up(2)))) {
                                    direction = Direction.SOUTH;
                                    lookDirection = 0f;
                                } else if(!client.world.isAir(client.player.getBlockPos().west())) {
                                    client.options.keyJump.setPressed(true);
                                }
                                break;
                        }

                        client.player.updatePositionAndAngles(client.player.getX(), client.player.getY(), client.player.getZ(), lookDirection, client.player.getPitch(client.getTickDelta()));

                        client.options.keyForward.setPressed(true);
                        client.options.keyBack.setPressed(false);
                        client.options.keyLeft.setPressed(false);
                        client.options.keyRight.setPressed(false);
                        client.options.keySneak.setPressed(false);
                }
            }
        }
    }
}
