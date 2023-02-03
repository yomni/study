package io.yomni.designpatterninspring.decorator.condiment;

import io.yomni.designpatterninspring.decorator.Beverage;

/**
 * 첨가물 데코레이터 슈퍼클래스
 */
public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}
