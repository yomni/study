package io.yomni.item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("HashCodeTest")
class HashCodeTest {

    @Test
    void 해시코드_검증() throws Exception {
        // given
        Map<Person, String> hashMap = new HashMap<>();

        Person person1 = new Person("John", 25);
        Person person2 = new Person("John", 25);

        String expected = "Developer";
        hashMap.put(person1, expected);

        // when / then
        assertThat(hashMap.get(person2)).isEqualTo(expected); // Developer
    }
}
