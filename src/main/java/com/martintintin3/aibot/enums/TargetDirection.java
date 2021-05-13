package com.martintintin3.aibot.enums;

public enum TargetDirection {
    NORTH(180f, null),
    SOUTH(0f, null),
    EAST(270f, null),
    WEST(90f, null),
    UP(null, -90f),
    DOWN(null, 90f);

    private final Float pitch;
    private final Float yaw;

    TargetDirection(Float yaw, Float pitch) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Float getPitch() {
        return this.pitch;
    }

    public Float getYaw() {
        return this.yaw;
    }
}
