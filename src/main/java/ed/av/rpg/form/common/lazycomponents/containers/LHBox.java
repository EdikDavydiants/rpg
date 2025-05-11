package ed.av.rpg.form.common.lazycomponents.containers;

import ed.av.rpg.form.common.lazycomponents.LContainer;
import ed.av.rpg.form.common.lazycomponents.LNode;
import javafx.scene.layout.HBox;

public class LHBox extends LContainer<HBox> {

    public LHBox() {
        super(HBox::new);
    }

    public LHBox(LNode<?>... nodes) {
        this();

        preInitAddAll(nodes);
    }
}
