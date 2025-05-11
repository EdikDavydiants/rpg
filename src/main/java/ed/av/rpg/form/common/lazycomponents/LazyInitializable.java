package ed.av.rpg.form.common.lazycomponents;

@FunctionalInterface
public interface LazyInitializable<T> {

    T getComponent();
}
