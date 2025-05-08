package ed.av.rpg.wiki;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CategoryButton extends ImageView {

    public static final int SIDE = 64;


    public CategoryButton(Image image) {
        super(image);
        setFitWidth(SIDE);
        setFitHeight(SIDE);
    }
}
