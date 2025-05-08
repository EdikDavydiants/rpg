package ed.av.rpg.module.common;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;

public class NamedField implements EnterField {

    private final TextField valueField;

    @Getter
    private final Pane pane;

    private NamedField(String fieldName, double wSize, Font font, Pane pane) {
        super();

        this.pane = pane;
        Text nameField = new Text(fieldName);
        nameField.setFont(font);
        nameField.prefWidth(wSize / 2);

        valueField = new TextField();
        valueField.prefWidth(wSize / 2);

        pane.getChildren().add(nameField);
        pane.getChildren().add(valueField);
    }

    public static NamedField getVertField(String fieldName, double wSize, Font font) {
        return new NamedField(fieldName, wSize, font, new VBox());
    }

    public static NamedField getHorField(String fieldName, double wSize, Font font) {
        return new NamedField(fieldName, wSize, font, new HBox());
    }

    @Override
    public String getValue() {
        return valueField.getText();
    }

    @Override
    public TextField getValueField() {
        return valueField;
    }
}
