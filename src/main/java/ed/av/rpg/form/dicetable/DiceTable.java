package ed.av.rpg.form.dicetable;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DiceTable extends Pane {

    private static final float REPLACING_TIME = 0.6F;
    public static final float STACK_THROW_AND_AREAS_PROPORTION = 0.6F;
    private ThrowArea throwArea;
    private StackArea stackArea;
    private final List<Dice> diceList = new ArrayList<>();

    public DiceTable(int height, int width) {

        setPrefHeight(height);
        setPrefWidth(width);

        setLayoutX(30);
        setLayoutY(30);

        setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }

    public DiceTable createAndSetThrowArea(int diceTableH, int diceTableW) {

        float throwAreaH = diceTableH * STACK_THROW_AND_AREAS_PROPORTION;
        throwArea = new ThrowArea((int) throwAreaH, diceTableW);
        int throwAreaX = 0;
        int throwAreaY = (int) (diceTableH - throwAreaH);
        throwArea.setLayoutX(throwAreaX);
        throwArea.setLayoutY(throwAreaY);
        throwArea.setThrowLimit(new Rectangle2D(throwAreaX, throwAreaY, diceTableW, throwAreaH));
        getChildren().add(throwArea);
        return this;
    }

    public DiceTable createAndSetStackArea(int diceTableH, int diceTableW) {

        float stackAreaH = diceTableH * (1f - STACK_THROW_AND_AREAS_PROPORTION);
        stackArea = new StackArea((int) stackAreaH, diceTableW);
        int stackAreaX = 0;
        int stackAreaY = 0;
        stackArea.getNode().setLayoutX(stackAreaX);
        stackArea.getNode().setLayoutY(stackAreaY);
        stackArea.createAndSetStackCells(stackAreaX, stackAreaY, diceTableW ,stackAreaH);
        getChildren().add(stackArea.getNode());
        return this;
    }

    public DiceTable createDices() {

        diceList.add(new Dice(4, "d4.png"));
        diceList.add(new Dice(6, "d6.png"));
        diceList.add(new Dice(8, "d8.png"));
        diceList.add(new Dice(10, "d10.png"));
        diceList.add(new Dice(12, "d12.png"));
        diceList.add(new Dice(20, "d20.png"));
        return this;
    }

    public DiceTable setDices() {

        //var stackPoints = stackArea.findStackPoints(0, 0, stackArea.width(), stackArea.height());
        var stackPoints = stackArea.findStackPoints(0, 0, 0, 0);

        for (int i = 0; i < diceList.size(); i++) {
            if (i < 5) {
                diceList.get(i).setStartPosition((int) stackPoints[i][0].x, (int) stackPoints[i][0].y);
            } else {
                diceList.get(i).setStartPosition((int) stackPoints[i - 5][1].x, (int) stackPoints[i - 5][1].y);
            }
        }

        for (Dice dice: diceList) {
            getChildren().add(dice);
            stackArea.addDice(dice);
        }

        return this;
    }

    private void replaceDiceToThrowArea(Dice dice) {

        stackArea.removeDice(dice);
        throwArea.addDice(dice);
        var dices = throwArea.getDiceList();
        var points = throwArea.findThrowPoints(dices.size());

        IntStream.range(0, dices.size())
                .forEach(i -> dices.get(i).animateMotion((int) points[i].x, (int) points[i].y, REPLACING_TIME, event -> {}));
    }

    private void replaceDiceToStackArea(Dice dice) {

        throwArea.removeDice(dice);
        stackArea.addDice(dice);
        var stackDices = stackArea.getDiceList();
        var throwDices = throwArea.getDiceList();
        var stackPoints = stackArea.findStackPoints(0, 0,  0,  0);
        //var stackPoints = stackArea.findStackPoints(0, 0, (int) stackArea.width(), (int) stackArea.height());
        var throwPoints = throwArea.findThrowPoints(throwDices.size());

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                if(stackDices.size() > i * 5 + j) {
                    stackDices.get(i * 5 + j).animateMotion((int) stackPoints[j][i].x, (int) stackPoints[j][i].y, REPLACING_TIME, event -> {});
                }
            }
        }

        IntStream.range(0, throwDices.size())
                .forEach(i -> throwDices.get(i).animateMotion((int) throwPoints[i].x, (int) throwPoints[i].y, REPLACING_TIME, event -> {}));
    }

    public void replaceDice(Dice dice) {

        if(stackArea.hasDice(dice)) {
            replaceDiceToThrowArea(dice);
        } else {
            replaceDiceToStackArea(dice);
        }
    }

    public Rectangle2D getThrowLimit() {
        return throwArea.getThrowLimit();
    }
}
