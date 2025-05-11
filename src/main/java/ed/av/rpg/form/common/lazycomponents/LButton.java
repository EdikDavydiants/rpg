package ed.av.rpg.form.common.lazycomponents;

import javafx.scene.control.Button;

public class LButton extends LControl<Button> {

    public LButton(LazyInitializable<Button> supplier) {
        super(supplier);
    }
}
