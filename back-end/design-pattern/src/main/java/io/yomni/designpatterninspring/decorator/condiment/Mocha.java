package io.yomni.designpatterninspring.decorator.condiment;

import io.yomni.designpatterninspring.decorator.Beverage;

/**
 * 모카 첨가물
 */
public class Mocha extends CondimentDecorator {
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 모카";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
