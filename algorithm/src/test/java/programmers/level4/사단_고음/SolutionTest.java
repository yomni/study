package programmers.level4.사단_고음;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


public class SolutionTest {

    @ParameterizedTest
    @CsvSource({
            "15, 1",
            "24, 0",
            "41, 2",
            "2147483647, 1735",
    })
    void solutionTest(int n, int answer) {
        Solution solution = new Solution();

        assertThat(solution.solution(n)).isEqualTo(answer);
    }
}
