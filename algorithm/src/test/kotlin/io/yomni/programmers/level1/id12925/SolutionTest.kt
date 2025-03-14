package io.yomni.programmers.level1.id12925

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
            Arguments.of("1234", 1234),
            Arguments.of("-1234", -1234),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: String, expected: Int) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }
}
