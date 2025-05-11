package ed.av.rpg.form.common.lazycomponents.controls;

import ed.av.rpg.form.common.lazycomponents.LControl;
import ed.av.rpg.form.common.lazycomponents.LazyInitializable;
import javafx.scene.control.TextField;

public class LTextField extends LControl<TextField> {

    public LTextField(LazyInitializable<TextField> supplier) {
        super(supplier);
    }
}
