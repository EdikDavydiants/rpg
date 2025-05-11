package ed.av.rpg.form.common.lazycomponents;

import javafx.scene.control.Label;

public class LLabel extends LControl<Label> {

    public LLabel(LazyInitializable<Label> supplier) {
        super(supplier);
    }
}
