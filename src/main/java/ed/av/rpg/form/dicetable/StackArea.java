package ed.av.rpg.form.dicetable;

import ed.av.rpg.form.common.lazycomponents.containers.LPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import ed.av.rpg.linearalgebra.Point2Df;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StackArea extends LPane {

    private final List<Dice> diceList = new ArrayList<>();

    public StackArea(int height, int width) {
        super();
        //super(width, height);

        getNode().setPrefHeight(height);
        getNode().setPrefWidth(width);

        getNode().setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
    }

    private DiceTable getDiceTable() {
        return (DiceTable) getNode().getParent();
    }

    public void createAndSetStackCells(int stackAreaX, int stackAreaY, int stackAreaW, float stackAreaH) {

        var stackPoints = findStackPoints(stackAreaX, stackAreaY, stackAreaW, stackAreaH);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                getNode().getChildren().add(new StackCell(Dice.WIDTH, stackPoints[j][i]));
            }
        }
    }

    public Point2Df[][] findStackPoints(int stackAreaX, int stackAreaY, float stackAreaW, float stackAreaH) {

        float gapH = (stackAreaH - 2 * Dice.HEIGHT) / 3;
        float[] cH = new float[2];
        cH[0] = gapH + (float) Dice.HEIGHT / 2;
        cH[1] = 2 * gapH + (float) (3 * Dice.HEIGHT) / 2;

        float gapW = (stackAreaW - 5 * Dice.WIDTH) / 6;
        float[] cW = new float[5];
        cW[0] = gapW + (float) Dice.WIDTH / 2;
        cW[1] = 2 * gapW + (float) (3 * Dice.WIDTH) / 2;
        cW[2] = 3 * gapW + (float) (5 * Dice.WIDTH) / 2;
        cW[3] = 4 * gapW + (float) (7 * Dice.WIDTH) / 2;
        cW[4] = 5 * gapW + (float) (9 * Dice.WIDTH) / 2;

        var stackPoints = new Point2Df[5][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                stackPoints[j][i] = new Point2Df(stackAreaX + cW[j] ,stackAreaY + cH[i]);
            }
        }

        return stackPoints;
    }

    public void addDice(Dice dice) {
        diceList.add(dice);
    }

    public void removeDice(Dice dice) {
        diceList.remove(dice);
    }

    public boolean hasDice(Dice dice) {
        return diceList.contains(dice);
    }

}
