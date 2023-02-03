package io.yomni.designpatterninspring.decorator.condiment;

import io.yomni.designpatterninspring.decorator.Beverage;

/**
 * 휘핑크림 첨가물
 */
public class Whip extends CondimentDecorator {
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 휘핑크림";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}