package ed.av.rpg.form.common.lazycomponents;

import javafx.scene.control.Control;

public class LControl<T extends Control> extends LNode<T> {

    public LControl(LazyInitializable<T> supplier) {
        super(supplier);
    }
}
