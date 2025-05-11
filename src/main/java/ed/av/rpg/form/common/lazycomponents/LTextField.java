package ed.av.rpg.form.common.lazycomponents;

import javafx.scene.control.TextField;

public class LTextField extends LControl<TextField> {

    public LTextField(LazyInitializable<TextField> supplier) {
        super(supplier);
    }
}
