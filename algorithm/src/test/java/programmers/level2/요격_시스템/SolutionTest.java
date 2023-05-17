package programmers.level2.요격_시스템;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void solution() {
        int[][] targets = new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        int result = 3;

        assertThat(new Solution().solution(targets)).isEqualTo(result);
    }
}