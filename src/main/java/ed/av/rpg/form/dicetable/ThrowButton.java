package ed.av.rpg.form.dicetable;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ThrowButton extends Text {

    public ThrowButton(String text) {
        super(text);
        setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24));
    }
}
