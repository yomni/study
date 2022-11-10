import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class DefaultMethodTest {
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
    void spliterator_테스트() {
        Spliterator<String> spliteratorPre = names.spliterator();
        Spliterator<String> spliteratorPost = spliteratorPre.trySplit();

        while (spliteratorPre.tryAdvance(System.out::println)) ;
        System.out.println("============");
        while (spliteratorPost.tryAdvance(System.out::println)) ;
    }
}
