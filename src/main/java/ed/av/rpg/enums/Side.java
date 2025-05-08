package ed.av.rpg.enums;

import java.util.Random;

public enum Side {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;

    public Side nextSideForDice() {
        return switch (this) {
            case LEFT ->  TOP;
            case TOP ->  RIGHT;
            case RIGHT -> BOTTOM;
            case BOTTOM ->  LEFT;
        };
    }

    public static Side randomSide() {
        return values()[new Random().nextInt(4)];
    }
}
