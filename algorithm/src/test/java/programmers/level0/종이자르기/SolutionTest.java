package programmers.level0.종이자르기;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {
    private Solution solution = new Solution();

    @Test
    void case1() {
        int m = 2;
        int n = 2;
        int result = 3;
        assertThat(solution.solution(m, n)).isEqualTo(result);
    }

    @Test
    void case2() {
        int m = 2;
        int n = 5;
        int result = 9;
        assertThat(solution.solution(m, n)).isEqualTo(result);
    }

    @Test
    void case3() {
        int m = 1;
        int n = 1;
        int result = 0;
        assertThat(solution.solution(m, n)).isEqualTo(result);
    }

    @Test
    void case4() {
        int m = 5;
        int n = 2;
        int result = 9;
        assertThat(solution.solution(m, n)).isEqualTo(result);
    }
}
