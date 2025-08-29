package com.aedii.asclepius.models.enums;

public enum Direction {
    UP, DOWN, RIGHT, LEFT ,ALL;

    public static Direction fromString(String value) {
        return switch (value.toLowerCase()) {
            case "up" -> UP;
            case "down"-> DOWN;
            case "left"-> LEFT;
            case "right"-> RIGHT;
            default -> ALL;
        };
    }
}
