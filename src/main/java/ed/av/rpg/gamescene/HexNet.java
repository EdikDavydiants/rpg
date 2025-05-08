package ed.av.rpg.gamescene;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import ed.av.rpg.GeneralUtils;
import ed.av.rpg.enums.HexDirection;
import ed.av.rpg.linearalgebra.Point2Df;
import ed.av.rpg.linearalgebra.Point2Di;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static ed.av.rpg.GeneralUtils.HEX_IMG_URL;
import static ed.av.rpg.GeneralUtils.HIGHLIGHTED_HEX_IMG_URL;
import static ed.av.rpg.gamescene.Hex.HEIGHT;
import static ed.av.rpg.gamescene.Hex.WIDTH;
import static ed.av.rpg.gamescene.Hex.getCenterX;
import static ed.av.rpg.gamescene.Hex.getCenterY;
import static ed.av.rpg.gamescene.Hex.getCoordX;
import static ed.av.rpg.gamescene.Hex.getCoordY;
import static ed.av.rpg.gamescene.HexNet.HexNetIteration.iterateIJ;

public class HexNet extends Pane {

    private final int wSize;
    private final int hSize;
    private final Hex[][] hexArr;

    public HexNet(int wSize, int hSize) {
        this.wSize = wSize;
        this.hSize = hSize;
        hexArr = new Hex[wSize][hSize];

        createHexes();
        setHexesPosition();

        setClickListener();
    }

    public void setHexesPosition() {
        HexNetIteration.iterateIJ(this, (i, j) -> {
            var hex = hexArr[i][j];
            var hexImgView = hex.getHexImgView();
            getChildren().add(hexImgView);

            hexImgView.setFitWidth(WIDTH);
            hexImgView.setFitHeight(HEIGHT);

            hexImgView.setLayoutX(getCoordX(i, j));
            hexImgView.setLayoutY(getCoordY(j));
        });
    }

    public void createHexes() {
        HexNetIteration.iterateIJ(this, (i, j) -> hexArr[i][j] = new Hex());
    }

    public int getWSize() {
        return wSize;
    }

    public int getHSize() {
        return hSize;
    }

    public Hex[][] getHexArr() {
        return hexArr;
    }

    private void setClickListener() {

        setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            int i_ = (int) (x / (3 * Hex.SIDE));
            int j_ = (int) (y / (Hex.HEIGHT / 2));

            var candidates = Arrays.stream(HexDirection.values())
                    .map(direction -> getNeighbourCoords(i_, j_, direction))
                    .collect(Collectors.toCollection(ArrayList::new));
            var candidates2 = Arrays.stream(HexDirection.values())
                    .map(direction -> getNeighbourCoords(i_ - 1, j_, direction))
                    .collect(Collectors.toCollection(ArrayList::new));

            candidates.addAll(candidates2);

            if(!isOutOfBounds(i_, j_)) {
                candidates.add(new Point2Di(i_, j_));
            }
            if (!isOutOfBounds(i_ - 1, j_)){
                candidates.add(new Point2Di(i_ - 1, j_));
            }


            Point2Di pCoords = candidates.stream()
                    .filter(Objects::nonNull)
                    .distinct()
                    .min((p1, p2) -> {
                        double p1cX = getCenterX(p1.x,  p1.y);
                        double p1cY = getCenterY(p1.y);
                        double p2cX = getCenterX(p2.x,  p2.y);
                        double p2cY = getCenterY(p2.y);
                        double l1 = Math.sqrt((x - p1cX) * (x - p1cX) + (y - p1cY) * (y - p1cY));
                        double l2 = Math.sqrt((x - p2cX) * (x - p2cX) + (y - p2cY) * (y - p2cY));
                        return Double.compare(l1, l2);
                    })
                    .orElseThrow(() -> new RuntimeException("Failed to find the nearest hex to a click!"));

            getHex(pCoords.x, pCoords.y).getHexImgView().setImage(new Image(HIGHLIGHTED_HEX_IMG_URL));
        });
    }

    public Point2Di getNeighbourCoords(int i, int j, HexDirection direction) {
        int iNeigh, jNeigh;
        switch (direction) {
            case UP -> {
                iNeigh = i;
                jNeigh = j - 2;
            }
            case RIGHT_UP -> {
                iNeigh = (j % 2 == 0) ? i : i + 1;
                jNeigh = j - 1;
            }
            case RIGHT_DOWN -> {
                iNeigh = (j % 2 == 0) ? i : i + 1;
                jNeigh = j + 1;
            }
            case DOWN -> {
                iNeigh = i;
                jNeigh = j + 2;
            }
            case LEFT_DOWN -> {
                iNeigh = (j % 2 == 1) ? i : i - 1;
                jNeigh = j + 1;
            }
            default -> {
                iNeigh = (j % 2 == 1) ? i : i - 1;
                jNeigh = j - 1;
            }
        }
        if (isOutOfBounds(iNeigh, jNeigh)) { return null; }
        return new Point2Di(iNeigh, jNeigh);
    }

    public Hex getNeighbourHex(int i, int j, HexDirection direction) {
        var coords = getNeighbourCoords(i, j, direction);
        return getHex(coords.x, coords.y);
    }

    public boolean isOutOfBounds(int i, int j) {
        return i < 0 || i >= wSize || j < 0 || j >= hSize;
    }

    public static class HexNetIteration {

        public static void forEach(HexNet hexNet, int wMax, int hMax, ActForEachInterface iterable) {
            if (wMax > hexNet.getWSize() || hMax > hexNet.getHSize()) {
                throw new RuntimeException("Out of bounds in HexNet iteration");
            }
            for (int i = 0; i < wMax; i++) {
                for (int j = 0; j < hMax; j++) {
                    iterable.doSmth(hexNet.getHex(i, j));
                }
            }
        }

        public static void forEach(HexNet hexNet, ActForEachInterface iterable) {
            for (int i = 0; i < hexNet.getWSize(); i++) {
                for (int j = 0; j < hexNet.getHSize(); j++) {
                    iterable.doSmth(hexNet.getHex(i, j));
                }
            }
        }

        public static void iterateIJ(HexNet hexNet, int wMax, int hMax, ActIJInterface iterable) {
            if (wMax > hexNet.getWSize() || hMax > hexNet.getHSize()) {
                throw new RuntimeException("Out of bounds in HexNet iteration");
            }
            for (int i = 0; i < wMax; i++) {
                for (int j = 0; j < hMax; j++) {
                    iterable.doSmth(i, j);
                }
            }
        }

        public static void iterateIJ(int wSize, int hSize, ActIJInterface iterable) {
            for (int i = 0; i < wSize; i++) {
                for (int j = 0; j < hSize; j++) {
                    iterable.doSmth(i, j);
                }
            }
        }

        public static void iterateIJ(HexNet hexNet, ActIJInterface iterable) {
            iterateIJ(hexNet.getWSize(), hexNet.getHSize(), iterable);
        }

        @FunctionalInterface
        public interface ActForEachInterface {

            void doSmth(Hex hex);
        }

        @FunctionalInterface
        public interface ActIJInterface {

            void doSmth(int i, int j);
        }

    }


    public Hex getHex(int i, int j) {
        if(isOutOfBounds(i, j)) { return null; }
        return hexArr[i][j];
    }

}
