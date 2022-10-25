package inflearn.sector5.queue.problem7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        assertEquals("YES", main.solution("CBA", "CBDAGE"));
        assertEquals("NO", main.solution("QWERTY", "AYKGDHEJF"));
        assertEquals("NO", main.solution("AKSDE" ,"KSDEYBCEGS"));
    }
}