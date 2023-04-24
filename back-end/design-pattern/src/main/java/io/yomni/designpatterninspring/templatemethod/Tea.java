package io.yomni.designpatterninspring.templatemethod;

public class Tea extends CaffeineBeverage {
    @Override
    protected void addCondiments() {
        System.out.println("레몬을 추가한다.");
    }

    @Override
    protected void brew() {
        System.out.println("끓는 물에 차를 우려낸다.");
    }

    @Override
    protected void hook() {
        System.out.println("홍차를 식히는 중...");
    }
}
