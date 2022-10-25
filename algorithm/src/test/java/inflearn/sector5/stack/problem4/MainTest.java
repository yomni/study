package inflearn.sector5.stack.problem4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.stream.events.DTD;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solution() {
        Main main = new Main();

        Assertions.assertEquals(12, main.solution("352+*9-"));
    }
}