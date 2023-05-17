package programmers.level1.가장_가까운_같은_글자;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    void case1() {
        String input = "banana";
        int[] result = new int[]{-1, -1, -1, 2, 2, 2};

        assertThat(solution.solution(input))
                .containsExactly(result);
    }

    @Test
    void case2() {
        String input = "foobar";
        int[] result = new int[]{-1, -1, 1, -1, -1, -1};

        assertThat(solution.solution(input))
                .containsExactly(result);
    }
}
