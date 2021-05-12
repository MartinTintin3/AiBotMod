package com.martintintin3.aibot;

import com.martintintin3.aibot.enums.AiType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Brain {
    private static Boolean enabled = false;
    private static MinecraftClient client;
    private static Vec3d startPosition;
    private static Direction direction;
    private static float lookDirection;
    private static Boolean didCheck = false;
    private static AiType aiType = AiType.NONE;
    public static Entity target = null;

    public static void start() {
        direction = client.player.getMovementDirection();
        client.player.sendMessage(new LiteralText("starting"), false);
        enabled = true;
        client.options.keyForward.setPressed(true);
        aiType = AiType.EXPLORE;
    }

    public static void stop() {
        client.player.sendMessage(new LiteralText("stoping"), false);
        client.options.keyForward.setPressed(false);
        client.options.keyLeft.setPressed(false);
        client.options.keyRight.setPressed(false);
        client.options.keyBack.setPressed(false);
        client.options.keySneak.setPressed(false);
        client.options.keyJump.setPressed(false);
        didCheck = false;
        enabled = false;
        aiType = AiType.NONE;
    }

    public static void tick(MinecraftClient client) {
        if(client.player == null) return;
        Brain.client = client;
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

            client.options.keyLeft.setPressed(false);
            client.options.keyRight.setPressed(false);
            client.options.keyForward.setPressed(false);
            client.options.keyBack.setPressed(false);

            client.player.updatePositionAndAngles(client.player.getX(), client.player.getY(), client.player.getZ(), 180f, client.player.getPitch(client.getTickDelta()));
            if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                if(Math.floor(playerPosition.x) + 0.6 < playerPosition.x) {
                    client.options.keySneak.setPressed(true);
                    client.options.keyLeft.setPressed(true);
                    return;
                } else if(Math.floor(playerPosition.x) + 0.4 > playerPosition.x) {
                    client.options.keySneak.setPressed(true);
                    client.options.keyRight.setPressed(true);
                    return;
                }
            } else {
                if(Math.floor(playerPosition.z) + 0.6 < playerPosition.z) {
                    client.options.keySneak.setPressed(true);
                    client.options.keyForward.setPressed(true);
                    return;
                } else if(Math.floor(playerPosition.z) + 0.4 > playerPosition.z) {
                    client.options.keySneak.setPressed(true);
                    client.options.keyBack.setPressed(true);
                    return;
                }
            }

            switch (aiType) {
                default:
                    aiType = AiType.NONE;
                    break;
                case EXPLORE:
                    switch (direction.asString()) {
                        case "north":
                            lookDirection = 180f;
                            if(!client.world.isAir(client.player.getBlockPos().north().up()) || (!client.world.isAir(client.player.getBlockPos().north()) && !client.world.isAir(client.player.getBlockPos().north().up(2)))) {
                                if(client.world.isAir(client.player.getBlockPos().west().up()) && (!client.world.isAir(client.player.getBlockPos().east().up()) || isDamagableFall(client.player.getBlockPos().east()))) {
                                    direction = Direction.WEST;
                                    lookDirection = 90f;
                                } else if(client.world.isAir(client.player.getBlockPos().east().up()) && (!client.world.isAir(client.player.getBlockPos().west().up()) || isDamagableFall(client.player.getBlockPos().west()))) {
                                    direction = Direction.EAST;
                                    lookDirection = 270f;
                                } else if(client.world.isAir(client.player.getBlockPos().west().up()) && client.world.isAir(client.player.getBlockPos().east().up()) && !(isDamagableFall(client.player.getBlockPos().east())) && !isDamagableFall(client.player.getBlockPos().west())) {
                                    int chance = (int) Math.round(Math.random());
                                    direction = chance == 1 ? Direction.WEST : Direction.EAST;
                                    lookDirection = chance == 1 ? 180f : 0f;
                                } else {
                                    direction = Direction.SOUTH;
                                    lookDirection = 0f;
                                }
                            } else if(!client.world.isAir(client.player.getBlockPos().north())) {
                                client.options.keyJump.setPressed(true);
                            }
                            break;
                        case "south":
                            lookDirection = 0f;
                            if(!client.world.isAir(client.player.getBlockPos().south().up()) || (!client.world.isAir(client.player.getBlockPos().south()) && !client.world.isAir(client.player.getBlockPos().south().up(2)))) {
                                if(client.world.isAir(client.player.getBlockPos().west().up()) && (!client.world.isAir(client.player.getBlockPos().east().up()) || isDamagableFall(client.player.getBlockPos().east()))) {
                                    direction = Direction.WEST;
                                    lookDirection = 90f;
                                } else if(client.world.isAir(client.player.getBlockPos().east().up()) && (!client.world.isAir(client.player.getBlockPos().west().up()) || isDamagableFall(client.player.getBlockPos().west()))) {
                                    direction = Direction.EAST;
                                    lookDirection = 270f;
                                } else if(client.world.isAir(client.player.getBlockPos().west().up()) && client.world.isAir(client.player.getBlockPos().east().up())) {
                                    int chance = (int) Math.round(Math.random());
                                    direction = chance == 1 ? Direction.WEST : Direction.EAST;
                                    lookDirection = chance == 1 ? 180f : 0f;
                                } else {
                                    direction = Direction.NORTH;
                                    lookDirection = 180f;
                                }
                            } else if(!client.world.isAir(client.player.getBlockPos().south())) {
                                client.options.keyJump.setPressed(true);
                            }
                            break;
                        case "east":
                            lookDirection = 270f;
                            if(!client.world.isAir(client.player.getBlockPos().east().up()) || (!client.world.isAir(client.player.getBlockPos().east()) && !client.world.isAir(client.player.getBlockPos().east().up(2)))) {
                                if(client.world.isAir(client.player.getBlockPos().north().up()) && !client.world.isAir(client.player.getBlockPos().south().up())) {
                                    direction = Direction.NORTH;
                                    lookDirection = 180f;
                                } else if(!client.world.isAir(client.player.getBlockPos().west().up()) && client.world.isAir(client.player.getBlockPos().east().up())) {
                                    direction = Direction.SOUTH;
                                    lookDirection = 0f;
                                } else if(client.world.isAir(client.player.getBlockPos().north().up()) && client.world.isAir(client.player.getBlockPos().south().up())) {
                                    int chance = (int) Math.round(Math.random());
                                    direction = chance == 1 ? Direction.NORTH : Direction.SOUTH;
                                    lookDirection = chance == 1 ? 180f : 0f;
                                } else {
                                    direction = Direction.WEST;
                                    lookDirection = 90f;
                                }
                            } else if(!client.world.isAir(client.player.getBlockPos().east())) {
                                client.options.keyJump.setPressed(true);
                            }
                            break;
                        case "west":
                            lookDirection = 90f;
                            if(!client.world.isAir(client.player.getBlockPos().west().up()) || (!client.world.isAir(client.player.getBlockPos().west()) && !client.world.isAir(client.player.getBlockPos().west().up(2)))) {
                                if(client.world.isAir(client.player.getBlockPos().north().up()) && !client.world.isAir(client.player.getBlockPos().south().up())) {
                                    direction = Direction.NORTH;
                                    lookDirection = 180f;
                                } else if(!client.world.isAir(client.player.getBlockPos().west().up()) && client.world.isAir(client.player.getBlockPos().east().up())) {
                                    direction = Direction.SOUTH;
                                    lookDirection = 0f;
                                } else if(client.world.isAir(client.player.getBlockPos().north().up()) && client.world.isAir(client.player.getBlockPos().south().up())) {
                                    int chance = (int) Math.round(Math.random());
                                    direction = chance == 1 ? Direction.NORTH : Direction.SOUTH;
                                    lookDirection = chance == 1 ? 180f : 0f;
                                } else {
                                    direction = Direction.EAST;
                                    lookDirection = 270f;
                                }
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
                    break;
            }
        }
    }

    private static Boolean isDamagableFall(BlockPos blockPos) {
        return client.world.isAir(blockPos.down(1)) &&
                client.world.isAir(blockPos.down(2)) &&
                client.world.isAir(blockPos.down(3)) &&
                client.world.isAir(blockPos.down(4));
    }
}
