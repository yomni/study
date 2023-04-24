package io.yomni.designpatterninspring.adaptor;

public class JPAirConditioner implements JPElectronicProduct {
    @Override
    public void connect() {
        System.out.println("일본에서 구매한 에어컨입니다. 110v 규격과 연결됩니다.");
    }
}
