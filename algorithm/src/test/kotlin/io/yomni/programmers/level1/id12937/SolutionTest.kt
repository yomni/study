package io.yomni.programmers.level1.id12937

import io.yomni.programmers.level1.id12937.Solution
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
            Arguments.of(3, "Odd"),
            Arguments.of(4, "Even"),
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(input: Int, expected: String) {
        val solution = Solution()
        assertThat(solution.solution(input)).isEqualTo(expected)
    }

}
