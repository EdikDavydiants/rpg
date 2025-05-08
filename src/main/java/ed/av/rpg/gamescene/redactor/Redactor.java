package ed.av.rpg.gamescene.redactor;

import javafx.scene.canvas.Canvas;
import ed.av.rpg.gamescene.ResistanceField;

public class Redactor extends Canvas {

    private final ResistanceField resistanceField;

    public Redactor(int wSize, int hSize) {
        resistanceField = new ResistanceField(wSize, hSize);
    }

    
}
