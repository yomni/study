package inflearn.sector6.sort.problem10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals(3,
                main.solution(5, 3, new int[]{1, 2, 8, 4, 9}));
    }
}