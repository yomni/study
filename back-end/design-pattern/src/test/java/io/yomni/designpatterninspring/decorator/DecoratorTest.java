package io.yomni.designpatterninspring.decorator;

import io.yomni.designpatterninspring.decorator.condiment.Mocha;
import io.yomni.designpatterninspring.decorator.condiment.Soy;
import io.yomni.designpatterninspring.decorator.condiment.Whip;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.LineNumberInputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    @Test
    void 데코레이터테스트() throws Exception {
        Beverage beverage = new Espresso(); // 1.99
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast(); // 0.99
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2); // 모카 2번 추가 = 0.99 + 0.20 + 0.20
        beverage2 = new Whip(beverage2); // 휘핑크림 추가 = = 0.99 + 0.20 + 0.20 + 0.10 = 1.49
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend(); // 0.89
        beverage3 = new Soy(beverage3); // 두유 추가 = 0.89 + 0.15
        beverage3 = new Mocha(beverage3); // 모카 추가 = 0.89 + 0.15 + 0.20
        beverage3 = new Whip(beverage3); // 휘핑크림 추가 = 0.89 + 0.15 + 0.20 + 0.10 = 1.34
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }

    @Test
    void IOStreamTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/test.txt");
        BufferedInputStream bis = new BufferedInputStream(fileInputStream);
        LineNumberInputStream lnis = new LineNumberInputStream(bis);
    }
}