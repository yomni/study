package io.yomni.designpatterninspring.templatemethod;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CaffeineBeverageTest {

    @Test
    void caffeineBeverageTest() throws Exception {
        // given
        CaffeineBeverage myTea = new Tea();
        CaffeineBeverage myCoffee = new Coffee();

        // when / then
        myTea.prepareRecipe();
        myCoffee.prepareRecipe();

        assertThat(myTea).isInstanceOf(CaffeineBeverage.class);
        assertThat(myCoffee).isInstanceOf(CaffeineBeverage.class);
    }
}