package ed.av.rpg.form.dicetable;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ed.av.rpg.enums.Side;
import javafx.util.Duration;

import java.util.Random;

import static ed.av.rpg.form.dicetable.ThrowArea.BOUNCE_NUMBER;
import static ed.av.rpg.enums.Side.BOTTOM;
import static ed.av.rpg.enums.Side.LEFT;
import static ed.av.rpg.enums.Side.RIGHT;
import static ed.av.rpg.enums.Side.TOP;

public class Dice extends Pane implements Runnable {

    public static final int HEIGHT = 35;
    public static final int WIDTH = 35;
    private final ImageView diceImage;
    private final Random random = new Random();
    private final int range;
    private volatile boolean isRunning = false;
    private volatile boolean isAnimating = false;
    private double v = 0;

    public Dice(int range, String imageUrl) {

        super();
        this.range = range;
        diceImage = new ImageView(imageUrl);
        diceImage.setFitHeight(HEIGHT);
        diceImage.setFitWidth(WIDTH);
        getChildren().add(diceImage);
        //diceImage.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24));
        //setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        setPrefHeight(HEIGHT);
        setPrefWidth(WIDTH);
        //setNumbInTheCenter();

        setOnMouseClicked(event -> getDiceTable().replaceDice(this));
    }

    public void setStartPosition(int x, int y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    private void setNumbInTheCenter() {
        diceImage.setLayoutX(15);
        diceImage.setLayoutY(30);
    }

    private void setNewTrajectory(int bounceNumber, Side side) {

        if(bounceNumber > 0) {
            int nextX;
            int nextY;
            switch(side) {
                case LEFT -> {
                    if(bounceNumber > 1) {
                        nextX = getLimit(LEFT);
                    } else {
                        nextX = getLimit(LEFT) + random.nextInt(getThrowAreaW() / 2);
                    }
                    nextY = getLimit(TOP) + random.nextInt(getThrowAreaH());
                }
                case TOP -> {
                    nextX = getLimit(LEFT) + random.nextInt(getLimit(RIGHT));
                    if(bounceNumber > 1) {
                        nextY = getLimit(TOP);
                    } else {
                        nextY = getLimit(TOP) + random.nextInt(getThrowAreaH() / 2);
                    }
                }
                case RIGHT -> {
                    if(bounceNumber > 1) {
                        nextX = getLimit(RIGHT);
                    } else {
                        nextX = getLimit(LEFT) + getThrowAreaW() / 2 + random.nextInt(getThrowAreaW() / 2);
                    }
                    nextY = getLimit(TOP) + random.nextInt(getThrowAreaH());
                }
                default -> {
                    nextX = getLimit(LEFT) + random.nextInt(getLimit(RIGHT));
                    if(bounceNumber > 1) {
                        nextY = getLimit(BOTTOM);
                    } else {
                        nextY = getLimit(TOP) + getThrowAreaH() / 2 + random.nextInt(getThrowAreaH() / 2);
                    }
                }
            }

            double nowX = getLayoutX();
            double nowY = getLayoutY();
            double distance = Math.sqrt((nowX - nextX) * (nowX - nextX) + (nowY - nextY) * (nowY - nextY));
            double time = (BOUNCE_NUMBER + 1 - bounceNumber) * distance / getThrowAreaW();
            v = distance / time;

            animateMotion(nextX, nextY, time,
                    event -> setNewTrajectory(bounceNumber - 1, side.nextSideForDice()));
        } else {
            stopDice();
        }
    }

    public void animateMotion(int nextX, int nextY, double time, EventHandler<ActionEvent> onFinished) {

        var keyValueX = new KeyValue(layoutXProperty(), nextX);
        var keyValueY = new KeyValue(layoutYProperty(), nextY);
        var keyFrame = new KeyFrame(Duration.seconds(time), keyValueX, keyValueY);
        var timeLine = new Timeline(keyFrame);
        timeLine.setOnFinished(event -> {
            isAnimating = false;
            onFinished.handle(event);
        });
        isAnimating = true;
        timeLine.play();
    }

    @Override
    public void run() {

        while (isRunning) {
            Platform.runLater(() -> setRotate(random.nextInt(360)));
            try {
                Thread.sleep((long) (70L * getThrowAreaW() / v));
            } catch (InterruptedException e) {
                throw new RuntimeException("exception");
            }
        }
    }

    public void stopDice() {
        isRunning = false;
    }

    public void throwDice() {

        setNewTrajectory(BOUNCE_NUMBER, Side.randomSide());
        isRunning = true;
        new Thread(this).start();
    }

    private int getLimit(Side side) {

        Rectangle2D throwLimit = getDiceTable().getThrowLimit();
        return (int) switch (side) {
            case LEFT -> throwLimit.getMinX();
            case TOP -> throwLimit.getMinY();
            case RIGHT -> throwLimit.getMaxX() - WIDTH;
            default -> throwLimit.getMaxY() - HEIGHT;
        };
    }

    private int getThrowAreaH() {
        return getLimit(BOTTOM) - getLimit(TOP);
    }

    private int getThrowAreaW() {
        return getLimit(RIGHT) - getLimit(LEFT);
    }

    private DiceTable getDiceTable() {
        return (DiceTable) getParent();
    }

    public boolean isAnimating() {
        return isAnimating;
    }
}
