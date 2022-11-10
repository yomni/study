import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    private List<String> names;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>();
        names.add("yomni");
        names.add("poco");
        names.add("pobi");
        names.add("satan");
    }

    @Test
    void lazy_테스트() {
        Stream<String> stringStream = names.stream()
                .map((name) -> {
                    System.out.println(name); // 중개오퍼레이션만 있으므로 실행되지 않는다. --> Lazy 하다의 의미
                    return name.toUpperCase();
                });
    }
}
