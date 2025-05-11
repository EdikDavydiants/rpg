package ed.av.rpg.form.common.lazycomponents.controls;

import ed.av.rpg.form.common.lazycomponents.LControl;
import ed.av.rpg.form.common.lazycomponents.LazyInitializable;
import javafx.scene.control.Label;

public class LLabel extends LControl<Label> {

    public LLabel(LazyInitializable<Label> supplier) {
        super(supplier);
    }
}
