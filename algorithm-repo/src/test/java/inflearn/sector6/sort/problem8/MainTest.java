package inflearn.sector6.sort.problem8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals(3,
                main.solution(8, 32, new int[]{23, 87, 65, 12, 57, 32, 99, 81}));
    }
}