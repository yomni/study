package io.yomni.programmers.level1.id12932

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("SolutionTest")
class SolutionTest {

    companion object {
        @JvmStatic
        fun argumentsProvider() = listOf(
            Arguments.of(12345, intArrayOf(5, 4, 3, 2, 1)),
            Arguments.of(98765, intArrayOf(5, 6, 7, 8, 9)),
            Arguments.of(10000, intArrayOf(0, 0, 0, 0, 1)),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: Long, expected: IntArray) {
        val solution = Solution()
        assertThat(solution.solutionSpeed(input)).isEqualTo(expected)
    }
}
