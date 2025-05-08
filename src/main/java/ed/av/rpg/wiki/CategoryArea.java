package ed.av.rpg.wiki;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class CategoryArea extends TilePane {

    public static final int GAP = 25;


    public CategoryArea(double hGap, double vGap) {

        super(hGap, vGap);
        setPrefColumns(3);
        setPadding(new Insets((double) GAP / 2, (double) GAP / 2, (double) GAP / 2, (double) GAP / 2));

        setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
    }
}
