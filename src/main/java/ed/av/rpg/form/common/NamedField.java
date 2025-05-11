package ed.av.rpg.form.common;

import ed.av.rpg.form.common.lazycomponents.LContainer;
import ed.av.rpg.form.common.lazycomponents.controls.LLabel;
import ed.av.rpg.form.common.lazycomponents.controls.LTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

public class NamedField implements EnterField {

    private final LTextField valueField;

    @Getter
    private final LContainer<?> container;

    private NamedField(String fieldName, double wSize, Font font, LContainer<?> container) {
        super();

        this.container = container;

        valueField = new LTextField(() -> {
            TextField tf = new TextField();
            tf.prefWidth(wSize / 2);
            return tf;
        });

        container.preInitAddAll(new LLabel(() -> {
            Label l = new Label(fieldName);
            l.setFont(font);
            l.prefWidth(wSize / 2);
            return l;
        }), valueField);
    }

    public static NamedField getVertField(String fieldName, double wSize, Font font) {

        LContainer<VBox> vBox = new LContainer<>(VBox::new);
        return new NamedField(fieldName, wSize, font, vBox);
    }

    public static NamedField getHorField(String fieldName, double wSize, Font font) {

        LContainer<HBox> hBox = new LContainer<>(HBox::new);
        return new NamedField(fieldName, wSize, font, hBox);
    }

    @Override
    public String getValue() {
        return valueField.getNode().getText();
    }

    @Override
    public TextField getValueField() {
        return valueField.getNode();
    }
}
