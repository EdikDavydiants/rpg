package ed.av.rpg.form.common.lazycomponents.controls;

import ed.av.rpg.form.common.lazycomponents.LControl;
import ed.av.rpg.form.common.lazycomponents.LazyInitializable;
import javafx.scene.control.TextArea;

public class LTextArea extends LControl<TextArea> {

    public LTextArea(LazyInitializable<TextArea> supplier) {
        super(supplier);
    }
}
