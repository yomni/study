package io.yomni.designpatterninspring.composite;

public class MenuItem implements MenuComponent {
    private String name;
    private String description;
    private int price;

    public MenuItem(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
