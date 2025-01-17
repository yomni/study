package io.yomni.programmers.level1.id12931

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
            Arguments.of(123, 6),
            Arguments.of(987, 24),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: Int, expected: Int) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }
}
