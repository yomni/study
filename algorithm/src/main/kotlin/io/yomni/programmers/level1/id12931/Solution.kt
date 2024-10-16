package io.yomni.programmers.level1.id12931

// https://school.programmers.co.kr/learn/courses/30/lessons/12931 - 자릿수 더하기

class Solution {

    fun solution(n: Int): Int {
        var sum = 0
        var number = n

        // 10으로 나누면서 자릿수를 추출
        while (number > 0) {
            sum += number % 10 // 현재 자릿수 (가장 오른쪽 자리)를 합산
            number /= 10       // 10으로 나누어 다음 자릿수로 이동
        }

        return sum
    }

    fun solutionShort(n: Int): Int {
        return n.toString()
            .map { it.toString().toInt() }
            .sum()
    }
}
