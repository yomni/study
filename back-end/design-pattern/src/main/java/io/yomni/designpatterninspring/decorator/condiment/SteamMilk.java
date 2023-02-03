package io.yomni.designpatterninspring.decorator.condiment;

import io.yomni.designpatterninspring.decorator.Beverage;

/**
 * 스팀밀크 첨가물
 */
public class SteamMilk extends CondimentDecorator {
    private Beverage beverage;

    public SteamMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 스팀밀크";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}