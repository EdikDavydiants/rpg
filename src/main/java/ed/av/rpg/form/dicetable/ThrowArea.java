package ed.av.rpg.form.dicetable;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ed.av.rpg.linearalgebra.Point2Df;

import java.util.ArrayList;
import java.util.List;

public class ThrowArea extends Pane {

    public static final int BOUNCE_NUMBER = 4;
    private final int height;
    private final int width;
    private Rectangle2D throwLimit;

    private final List<Dice> diceList = new ArrayList<>();

    public ThrowArea(int height, int width) {
        this.height = height;
        this.width = width;
        setPrefHeight(height);
        setPrefWidth(width);

        setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
        createAndSetThrowButton();
    }

    private Point2Df getCenter() {
        return new Point2Df((float) (throwLimit.getMinX() + width / 2), (float) (throwLimit.getMinY() + height / 2));
    }

    public Point2Df[] findThrowPoints(int N) {

        if (N < 1) {
            throw new RuntimeException("N must be > 0");
        }

        var center = getCenter();
        double dFi = 2 * Math.PI / N;
        float r = Math.min((float) width, height) / 4;
        if(N == 1) { r = 0; }

        var points = new Point2Df[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point2Df((float) (center.x + r * Math.sin(i * dFi)),
                    (float) (center.y + r * Math.cos(i * dFi)));
        }

        return points;
    }

    public Rectangle2D getThrowLimit() {
        return throwLimit;
    }

    public void setThrowLimit(Rectangle2D throwLimit) {
        this.throwLimit = throwLimit;
    }

    public void addDice(Dice dice) {
        diceList.add(dice);
    }

    public void removeDice(Dice dice) {
        diceList.remove(dice);
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public void throwDices() {
        diceList.forEach(Dice::throwDice);
    }

    private void createAndSetThrowButton() {

        var throwButton = new ThrowButton("throw");
        getChildren().add(throwButton);
        throwButton.setOnMouseClicked(event -> {
            if (!isAnimating()) { throwDices(); }
        });
    }

    public boolean isAnimating() {
        return diceList.stream()
                .anyMatch(Dice::isAnimating);
    }

    public DiceTable getDiceTable() {
        return (DiceTable) getParent();
    }
}
