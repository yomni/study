package inflearn.sector6.sort.problem9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals(17,
                main.solution(9, 3, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}