package programmers.level0.다음에_올_숫자;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Test
    void test1() {
        // given / when
        int answer = solution.solution(new int[]{1, 2, 3, 4});

        // then
        assertThat(answer).isEqualTo(5);
    }

    @Test
    void test2() {
        // given / when
        int answer = solution.solution(new int[]{2, 4, 8});

        // then
        assertThat(answer).isEqualTo(16);
    }

    @Test
    void test3() {
        // 공차가 0인 경우?
        // given / when
        int answer = solution.solution(new int[]{0, 0, 0});

        // then
        assertThat(answer).isEqualTo(0);
    }
}