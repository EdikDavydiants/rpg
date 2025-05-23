package ed.av.rpg.gamescene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ed.av.rpg.util.GeneralUtils;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Hex {

    public static final double SIDE = 40;
    public static final double WIDTH = 2 * SIDE;
    public static final double HEIGHT = 2 * SIDE * cos(PI / 6);
    public static final double SHIFTING = 1.5 * SIDE;
    public static final Image hexImg = new Image(GeneralUtils.HEX_IMG_URL);
    private ImageView view = null;

    public ImageView getHexImgView() {
        if (view == null) {
            view = new ImageView(hexImg);
        }
        return view;
    }

    public static double getCoordX(int i, int j) {
        double shifting = (j % 2 == 0) ? 0 : SHIFTING;
        return i * 3 * Hex.SIDE + shifting;
    }

    public static double getCoordY(int j) {
        return j * Hex.HEIGHT / 2;
    }

    public static double getCenterX(int i, int j) {
        return getCoordX(i, j) + WIDTH / 2;
    }

    public static double getCenterY(int j) {
        return getCoordY(j) + HEIGHT / 2;
    }
}
