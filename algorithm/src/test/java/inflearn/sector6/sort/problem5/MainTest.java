package inflearn.sector6.sort.problem5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals("D"
                , main.solution(5, new int[]{20, 25, 52, 30, 39, 33, 43, 33}));
    }
}