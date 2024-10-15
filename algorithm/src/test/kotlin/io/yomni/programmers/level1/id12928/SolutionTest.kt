package io.yomni.programmers.level1.id12928

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
            Arguments.of(12, 28),
            Arguments.of(5, 6),
            Arguments.of(3000, 9360),
            Arguments.of(28, 56),
            Arguments.of(100, 217),
            Arguments.of(1, 1),
            Arguments.of(36, 91)
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: Int, expected: Int) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }
}
