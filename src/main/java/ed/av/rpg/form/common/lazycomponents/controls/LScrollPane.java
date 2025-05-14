package ed.av.rpg.form.common.lazycomponents.controls;

import ed.av.rpg.form.common.lazycomponents.LContainer;
import ed.av.rpg.form.common.lazycomponents.LControl;
import ed.av.rpg.form.common.lazycomponents.LazyInitializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class LScrollPane extends LControl<ScrollPane> {

    private LContainer<?> lContent;

    public LScrollPane(LazyInitializable<ScrollPane> supplier) {
        super(supplier);
    }

    @Override
    public void initialize() {
        if (lContent == null) {
            throw new RuntimeException("ScrollPane must contain a content!");
        }
        super.initialize();
        lContent.initialize();
        Pane content = lContent.getNode();
        getNode().setContent(content);
    }

    public void setVValue(double value) {
        getNode().setVvalue(value);
    }

    public void setContent(LContainer<?> lContent) {
        this.lContent = lContent;
    }
}
