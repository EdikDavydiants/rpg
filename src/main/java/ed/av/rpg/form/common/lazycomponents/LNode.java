package ed.av.rpg.form.common.lazycomponents;

import javafx.application.Platform;
import javafx.scene.Node;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static ed.av.rpg.util.StringConstants.COMPONENT_NOT_INITIALIZED;

@RequiredArgsConstructor
public abstract class LNode<T extends Node> {

    private T node;

    @Getter
    private final LazyInitializable<T> supplier;

    public void initialize() {
        initComponent();
    }

    public void initComponent() {
        node = supplier.getComponent();
    }

    public T getNode() {
        checkComponentInitialized();
        return node;
    }

    public void checkComponentInitialized() {
        if (node == null) {
            throw new RuntimeException(COMPONENT_NOT_INITIALIZED);
        }
    }

    public void close() {
        Platform.runLater(() -> node.setVisible(false));
    }

    public void open() {
        Platform.runLater(() -> node.setVisible(true));
    }
}
