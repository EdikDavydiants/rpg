package ed.av.rpg.form.common.lazycomponents;

import javafx.scene.layout.Pane;

public class LPane extends LContainer<Pane> {

    public LPane() {
        super(Pane::new);
    }

    public LPane(LNode<?>... nodes) {
        this();

        preInitAddAll(nodes);
    }
}
