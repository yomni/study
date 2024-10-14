package io.yomni.programmers.level1.`12954`

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
            Arguments.of(2, 5, listOf(2L, 4L, 6L, 8L, 10L)), // List<Long>로 변경
            Arguments.of(4, 3, listOf(4L, 8L, 12L)),         // List<Long>로 변경
            Arguments.of(-4, 2, listOf(-4L, -8L)),           // List<Long>로 변경
        )
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    fun solution(x: Int, n: Int, expected: List<Long>) { // LongArray에서 List<Long>으로 변경
        val solution = Solution()
        assertThat(solution.solution(x, n)).hasSameElementsAs(expected) // containsExactlyElementsOf 사용
    }
}
