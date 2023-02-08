package io.yomni.designpatterninspring.factory;

import io.yomni.designpatterninspring.factory.pizza.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaStore {
    private static final Logger logger = LoggerFactory.getLogger(PizzaStore.class);

    public Pizza orderPizza(String type) {
        Pizza pizza = SimplePizzaFactory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
