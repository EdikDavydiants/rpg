package ed.av.rpg.form.common.lazycomponents.controls;

import ed.av.rpg.form.common.lazycomponents.LControl;
import ed.av.rpg.form.common.lazycomponents.LazyInitializable;
import javafx.scene.control.Button;

public class LButton extends LControl<Button> {

    public LButton(LazyInitializable<Button> supplier) {
        super(supplier);
    }
}
