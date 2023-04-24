package io.yomni.designpatterninspring.adaptor;

public class AdapterForKoreaElectronicProduct implements KRElectronicProduct {
    private final JPElectronicProduct jpElectronicProduct;

    public AdapterForKoreaElectronicProduct(JPElectronicProduct jpElectronicProduct) {
        this.jpElectronicProduct = jpElectronicProduct;
    }

    @Override
    public void connect() {
        System.out.println("어댑터입니다. 외부에서 220v 전압을 받아 110v로 변경합니다.");
        jpElectronicProduct.connect();
    }
}
