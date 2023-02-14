package io.yomni.designpatterninspring.templatemethod;

public class Coffee extends CaffeineBeverage {
    @Override
    protected void brew() {
        System.out.println("필터로 커피를 우려낸다.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("설탕과 커피를 추가한다.");
    }
}
