package io.yomni.designpatterninspring.adaptor;

public class KRRefrigerator implements KRElectronicProduct {
    @Override
    public void connect() {
        System.out.println("한국에서 구매한 냉장고입니다. 220v 와 연결됩니다.");
    }
}
