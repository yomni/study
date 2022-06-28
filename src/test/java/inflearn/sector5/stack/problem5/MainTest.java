package inflearn.sector5.stack.problem5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();
        Assertions.assertEquals(17, main.solution("()(((()())(())()))(())"));
        Assertions.assertEquals(24, main.solution("(((()(()()))(())()))(()())"));
    }
}