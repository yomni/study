package io.yomni.programmers.level1.id12912

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
            Arguments.of(3, 5, 12L),
            Arguments.of(3, 3, 3L),
            Arguments.of(5, 3, 12L),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(a: Int, b: Int, expected: Long) {
        val solution = Solution()
        assertThat(solution.solution(a, b)).isEqualTo(expected)
    }
}
