package io.yomni.designpatterninspring.adaptor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdapterTest {
    @Test
    void adapterTest() throws Exception {
        // given
        JPElectronicProduct airConditioner = new JPAirConditioner();
        KRElectronicProduct refrigerator = new KRRefrigerator();

        // when
        KRElectronicProduct adapterWithJapanElectronicProduct = new AdapterForKoreaElectronicProduct(airConditioner);

        // then
        connectWithKoreaElectric(adapterWithJapanElectronicProduct);
        connectWithKoreaElectric(refrigerator);

        assertThat(adapterWithJapanElectronicProduct).isInstanceOf(KRElectronicProduct.class);
    }

    public void connectWithKoreaElectric(KRElectronicProduct product) {
        System.out.println("한국 정격전압은 220v을 사용합니다.");
        product.connect();
    }
}