package programmers.level1.영단어변경;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void solution() {
        Solution solution = new Solution();

        // tc1
        int answer = solution.solution("one4seveneight");
        assertThat(answer).isEqualTo(1478);

        // tc2
        answer = solution.solution("23four5six7");
        assertThat(answer).isEqualTo(234567);

        // tc3
        answer = solution.solution("2three45sixseven");
        assertThat(answer).isEqualTo(234567);

        // tc4
        answer = solution.solution("123");
        assertThat(answer).isEqualTo(123);
    }
}