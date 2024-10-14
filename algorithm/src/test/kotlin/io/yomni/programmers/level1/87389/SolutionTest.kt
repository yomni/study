package io.yomni.programmers.level1.`87389`

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
            Arguments.of(10, 3),
            Arguments.of(12, 11),
            Arguments.of(3, 2),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: Int, expected: Int) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }
}
