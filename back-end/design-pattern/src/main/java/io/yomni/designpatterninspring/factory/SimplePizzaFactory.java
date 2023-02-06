package io.yomni.designpatterninspring.factory;

import io.yomni.designpatterninspring.factory.pizza.CheesePizza;
import io.yomni.designpatterninspring.factory.pizza.GreekPizza;
import io.yomni.designpatterninspring.factory.pizza.PepperoniPizza;
import io.yomni.designpatterninspring.factory.pizza.Pizza;

public class SimplePizzaFactory {
    public static Pizza createPizza(String type) {
        Pizza pizza = null;

        if ("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if ("greek".equals(type)) {
            pizza = new GreekPizza();
        } else if ("pepperoni".equals(type)) {
            pizza = new PepperoniPizza();
        }
        return pizza;
    }
}
