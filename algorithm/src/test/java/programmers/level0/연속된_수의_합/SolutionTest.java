package programmers.level0.연속된_수의_합;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Test
    void test1() {
        int[] answers = solution.solution(3, 12);
        assertThat(answers).containsExactly(new int[]{3, 4, 5});
    }

    @Test
    void test2() {
        int[] answers = solution.solution(5, 15);
        assertThat(answers).containsExactly(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void test3() {
        int[] answers = solution.solution(4, 14);
        assertThat(answers).containsExactly(new int[]{2, 3, 4, 5});
    }

    @Test
    void test4() {
        int[] answers = solution.solution(5, 5);
        assertThat(answers).containsExactly(new int[]{-1, 0, 1, 2, 3});
    }

    @Test
    void test5() {
        int[] answers = solution.solution(3, 0);
        assertThat(answers).containsExactly(new int[]{-1, 0, 1});
    }
}
