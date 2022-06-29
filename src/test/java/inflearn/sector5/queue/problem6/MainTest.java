package inflearn.sector5.queue.problem6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();
        Assertions.assertEquals(7, main.solution(8, 3));
        Assertions.assertEquals(91, main.solution(100, 3));
        Assertions.assertEquals(494, main.solution(500, 8));
        Assertions.assertEquals(212, main.solution(700, 9));
        Assertions.assertEquals(329, main.solution(1000, 9));
    }
}