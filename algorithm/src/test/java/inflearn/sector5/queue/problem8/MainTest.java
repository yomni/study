package inflearn.sector5.queue.problem8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

//        Assertions.assertEquals(3, main.solution(5, 2, new int[]{60, 50, 70, 80, 90}));
        Assertions.assertEquals(5, main.solution(6, 0, new int[]{60, 60, 90, 60, 60, 60}));
//        Assertions.assertEquals(10, main.solution(15, 5, new int[]{63, 53, 87, 91, 83, 72, 83, 92, 63, 68, 88, 74, 51, 82, 89}));
    }
}
