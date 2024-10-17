package io.yomni.programmers.level1.id12932

// https://school.programmers.co.kr/learn/courses/30/lessons/12932 - 자연수 뒤집어 배열로 만들기

class Solution {
    fun solution(n: Long): IntArray {
        return n.toString()
            .reversed()
            .map { it.toString().toInt() }
            .toIntArray()
    }

    /**
     * - Stream 사용 시 오버헤드: Stream은 내부적으로 다양한 최적화를 지원하지만,
     *     고차 함수와 람다를 사용할 때마다 객체를 생성하고 함수 호출을 수행해야 합니다. 이러한 오버헤드가 반복문에 비해 성능을 저하시킬 수 있습니다.
     * - 반복문: 반복문은 각 요소를 직접적으로 접근하여 처리하므로,
     *     추가적인 객체 생성을 피할 수 있습니다. 따라서 성능이 더 우수할 수 있습니다, 특히 대량의 데이터 처리를 하는 경우에 더욱 그렇습니다.
     *
     * --> 가독성 vs 성능
     */
    fun solutionSpeed(n: Long): IntArray {
        val result = mutableListOf<Int>()

        // n이 0이 될 때까지 반복
        var number = n
        while (number > 0) {
            result.add((number % 10).toInt()) // 가장 오른쪽 자리 숫자를 추출하여 추가
            number /= 10 // 10으로 나누어 다음 자릿수로 이동
        }

        return result.toIntArray() // List<Int>를 IntArray로 변환
    }
}
