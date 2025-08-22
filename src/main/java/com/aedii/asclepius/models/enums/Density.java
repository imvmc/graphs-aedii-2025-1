package com.aedii.asclepius.models.enums;


import java.util.Random;

public enum Density {
    LOW(1),
    MEDIUM(1.2),
    HIGH(1.4);

    private final int weight;

    Density(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    private static final Random rand = new Random();

    public static Density randomDensity() {
        int r = rand.nextInt(100); // número de 0 a 99

        if (r < 50) {
            return LOW;     // 50%
        } else if (r < 70) {
            return MEDIUM;  // 20%
        } else {
            return HIGH;    // 30%
        }
    }
}
