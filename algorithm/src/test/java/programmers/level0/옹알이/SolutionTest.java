package programmers.level0.옹알이;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void solution() {
        Solution solution = new Solution();

        // tc1
        int answer = solution.solution(new String[]{"aya", "yee", "u", "maa", "wyeoo"});
        assertThat(answer).isEqualTo(1);

        // tc2
        answer = solution.solution(new String[]{"ayaye", "uuuma", "ye", "yemawoo", "ayaa"});
        assertThat(answer).isEqualTo(3);
    }
}