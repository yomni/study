package programmers.level1.크기가작은_부분문자열;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {
    private Solution solution = new Solution();

    @Test
    void case1() {
        String t = "3141592";
        String p = "271";
        int result = 2;

        assertThat(solution.solution(t, p)).isEqualTo(result);
    }

    @Test
    void case2() {
        String t = "500220839878";
        String p = "7";
        int result = 8;

        assertThat(solution.solution(t, p)).isEqualTo(result);
    }

    @Test
    void case3() {
        String t = "10203";
        String p = "15";
        int result = 3;

        assertThat(solution.solution(t, p)).isEqualTo(result);
    }
}
