package ed.av.rpg.form.common;

import ed.av.rpg.form.common.lazycomponents.LContainer;
import ed.av.rpg.form.common.lazycomponents.controls.LLabel;
import ed.av.rpg.form.common.lazycomponents.controls.LTextField;
import javafx.application.Platform;
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

    public NamedField(String fieldName, double wSize, Font font, LContainer<?> container,
                       TextListener listener) {
        super();

        this.container = container;

        valueField = new LTextField(() -> {
            TextField tf = new TextField();
            tf.prefWidth(wSize / 2);
            if (listener != null) {
                tf.textProperty().addListener((observable, oldText, newText) -> {
                    listener.textChanged(getValueField(), oldText, newText);
                });
            }
            return tf;
        });

        container.preInitAddAll(new LLabel(() -> {
            Label l = new Label(fieldName);
            l.setFont(font);
            l.prefWidth(wSize / 2);
            return l;
        }), valueField);
    }

    private NamedField(String fieldName, double wSize, Font font, LContainer<?> container) {
        this(fieldName, wSize, font, container, null);
    }

    public static NamedField getVertField(String fieldName, double wSize, Font font) {
        return new NamedField(fieldName, wSize, font, new LContainer<>(VBox::new));
    }

    public static NamedField getHorField(String fieldName, double wSize, Font font) {
        return new NamedField(fieldName, wSize, font, new LContainer<>(HBox::new));
    }

    public static NamedField getVertFieldWithListener(String fieldName, double wSize, Font font,
                                                      TextListener textChangeListener) {
        return new NamedField(fieldName, wSize, font, new LContainer<>(VBox::new), textChangeListener);
    }

    public static NamedField getHorFieldWithListener(String fieldName, double wSize, Font font,
                                                     TextListener textChangeListener) {
        return new NamedField(fieldName, wSize, font, new LContainer<>(HBox::new), textChangeListener);
    }

    @Override
    public String getValue() {
        return valueField.getNode().getText();
    }

    @Override
    public TextField getValueField() {
        return valueField.getNode();
    }

    public void clearField() {
        Platform.runLater(() -> valueField.getNode().setText(""));
    }

    @FunctionalInterface
    public interface TextListener {
        void textChanged(TextField textField, String oldText, String newText);
    }
}
