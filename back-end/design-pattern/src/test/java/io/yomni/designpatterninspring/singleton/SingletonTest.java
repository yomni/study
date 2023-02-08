package io.yomni.designpatterninspring.singleton;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {

    @Test
    void singleton_test() throws Exception {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertThat(instance1 == instance2).isTrue();
    }

}