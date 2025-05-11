package ed.av.rpg.form.dicetable;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ed.av.rpg.linearalgebra.Point2Df;

public class StackCell extends Pane {

    public StackCell(int width, Point2Df stackPoint) {

        super();

        setPrefHeight(width);
        setPrefWidth(width);

        setLayoutX(stackPoint.x);
        setLayoutY(stackPoint.y);


        setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
    }

    private DiceTable getDiceTable() {
        return ((ThrowArea) getParent()).getDiceTable();
    }
}
