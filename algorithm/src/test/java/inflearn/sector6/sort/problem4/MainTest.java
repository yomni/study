package inflearn.sector6.sort.problem4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        assertArrayEquals(new int[]{7, 5, 3, 2, 6}
                , main.solution(5, 9, new int[]{1, 2, 3, 2, 6, 2, 3, 5, 7}));
    }
}