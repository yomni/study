package io.yomni.programmers.level1.id12912

// https://school.programmers.co.kr/learn/courses/30/lessons/12912 - 두 정수의 합

class Solution {

    fun solution(a: Int, b: Int): Long {
        // a와 b의 작은 값과 큰 값을 찾음
        val start = Math.min(a, b)
        val end = Math.max(a, b)

        // start부터 end까지의 합을 계산
        var sum = 0L
        for (i in start..end) {
            sum += i
        }

        return sum
    }
}
