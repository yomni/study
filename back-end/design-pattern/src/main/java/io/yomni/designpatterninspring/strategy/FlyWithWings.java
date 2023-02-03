package io.yomni.designpatterninspring.strategy;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("날고있어요!");
    }
}
