package io.yomni.designpatterninspring.templatemethod;

public abstract class CaffeineBeverage {

    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
        hook();
    }

    protected void hook() {
        // 아무것도 구현하지 않은 메소드
        // Hook 메소드를 통해 서브클래스에게 조금 더 확장할 수 있는 기회를 준다.
    }

    private void boilWater() {
        System.out.println("물을 끓인다");
    }

    private void pourInCup() {
        System.out.println("음료를 컵에 따른다.");
    }

    protected abstract void brew();

    protected abstract void addCondiments();
}
