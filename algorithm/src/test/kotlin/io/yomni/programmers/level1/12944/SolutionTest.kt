package io.yomni.programmers.level1.`12944`

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
            Arguments.of(intArrayOf(1, 2, 3, 4), 2.5),
            Arguments.of(intArrayOf(5), 5),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: IntArray, expected: Double) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }
}
