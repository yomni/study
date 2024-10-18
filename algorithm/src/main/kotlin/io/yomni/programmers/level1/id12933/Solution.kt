package io.yomni.programmers.level1.id12933

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12933 - 정수 내림차순으로 배치하기
 */
class Solution {
    fun solution(n: Long): Long {
        return n.toString()
            .map { it.toString().toInt() } // 각 문자(자릿수)를 Int로 변환
            .sortedDescending() // 큰 것부터 작은 순으로 정렬
            .joinToString("") // Int 리스트를 문자열로 결합
            .toLong() // 최종적으로 Long으로 변환
    }

    fun solutionShort(n: Long): Long {
        val digits = mutableListOf<Int>()

        // n을 10으로 나누면서 각 자릿수를 추출
        var number = n
        while (number > 0) {
            digits.add((number % 10).toInt()) // 현재 자릿수(가장 오른쪽)를 추출하여 추가
            number /= 10 // 10으로 나누어 다음 자릿수로 이동
        }

        // 자릿수 정렬 (내림차순)
        for (i in 0 until digits.size) {
            for (j in 0 until digits.size - 1 - i) {
                if (digits[j] < digits[j + 1]) { // 큰 값이 앞에 오도록 스와핑
                    val temp = digits[j]
                    digits[j] = digits[j + 1]
                    digits[j + 1] = temp
                }
            }
        }

        // 정렬된 자릿수를 하나의 숫자로 결합
        var result = 0L
        for (digit in digits) {
            result = result * 10 + digit // 자릿수를 조합하여 숫자로 만들기
        }

        return result
    }
}
