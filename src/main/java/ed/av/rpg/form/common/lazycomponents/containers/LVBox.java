package ed.av.rpg.form.common.lazycomponents.containers;

import ed.av.rpg.form.common.lazycomponents.LContainer;
import ed.av.rpg.form.common.lazycomponents.LNode;
import ed.av.rpg.form.common.lazycomponents.LazyInitializable;
import javafx.scene.layout.VBox;

public class LVBox extends LContainer<VBox> {

    public LVBox() {
        super(VBox::new);
    }

    public LVBox(LazyInitializable<VBox> supplier) {
        super(supplier);
    }

    public LVBox(LNode<?>... nodes) {
        this();

        preInitAddAll(nodes);
    }
}
