package io.yomni.designpatterninspring.composite;

public interface MenuComponent {
    default void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    default String getDescription() {
        throw new UnsupportedOperationException();
    }

    default int getPrice() {
        throw new UnsupportedOperationException();
    }
}
