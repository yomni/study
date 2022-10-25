package inflearn.sector6.sort.problem6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        List<Integer> solution = main.solution(9, new int[]{120, 125, 152, 130, 135, 135, 143, 127, 160});

        Assertions.assertEquals(solution.get(0), 3);
        Assertions.assertEquals(solution.get(1), 8);
    }
}