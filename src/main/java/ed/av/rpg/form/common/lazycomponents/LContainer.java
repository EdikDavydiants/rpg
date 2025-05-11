package ed.av.rpg.form.common.lazycomponents;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class LContainer<T extends Pane> extends LNode<T> {

    private final List<LNode<?>> children = new ArrayList<>();

    public LContainer(LazyInitializable<T> supplier) {
        super(supplier);
    }

    @Override
    public void initialize() {

        super.initialize();
        initChildren();
    }

    public void initChildren() {

        children.forEach(LNode::initialize);
        children.forEach(child -> getNode().getChildren().add(child.getNode()));
    }

    public void preInitAddAll(LNode<?>... childs) {

        children.addAll(Arrays.asList(childs));
    }

    public void postInitAddAll(Node... childs) {

        getNode().getChildren().addAll(Arrays.asList(childs));
    }
}
