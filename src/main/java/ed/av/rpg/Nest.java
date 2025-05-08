package ed.av.rpg;

import javafx.scene.layout.Pane;

public class Nest extends Pane {

    private final float width;
    private final float height;

    public Nest(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float width() {
        return width;
    }

    public float height() {
        return height;
    }
}
