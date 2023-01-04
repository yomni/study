package programmers.level1.스마트폰키패드;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SolutionTest {

    @Test
    void name() {
        Solution solution = new Solution();
        // tc1
        String answer = solution.solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
        assertThat(answer).isEqualTo("LRLLLRLLRRL");

        // tc2
        answer = solution.solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left");
        assertThat(answer).isEqualTo("LRLLRRLLLRR");

        // tc3
        answer = solution.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right");
        assertThat(answer).isEqualTo("LLRLLRLLRL");
    }
}
