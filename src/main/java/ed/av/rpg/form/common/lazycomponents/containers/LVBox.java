package ed.av.rpg.form.common.lazycomponents.containers;

import ed.av.rpg.form.common.lazycomponents.LContainer;
import ed.av.rpg.form.common.lazycomponents.LNode;
import javafx.scene.layout.VBox;

public class LVBox extends LContainer<VBox> {

    public LVBox() {
        super(VBox::new);
    }

    public LVBox(LNode<?>... nodes) {
        this();

        preInitAddAll(nodes);
    }
}
