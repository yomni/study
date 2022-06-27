package inflearn.sector5.stack.problem2;

import inflearn.sector5.stack.problem2.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals("EFLM", main.solution("(A(BC)D)EF(G(H)(IJ)K)LM(N)"));
    }
}