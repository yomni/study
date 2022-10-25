package inflearn.sector5.stack.problem1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals("NO", main.solution("(()(()))(()"));
        Assertions.assertEquals("YES", main.solution("()"));
        Assertions.assertEquals("NO", main.solution("(()"));
    }
}