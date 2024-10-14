package io.yomni.programmers.level1.`12944`

// https://school.programmers.co.kr/learn/courses/30/lessons/12944 - 평균 구하기
class Solution {
    fun solution(arr: IntArray): Double {
        return arr.average()
    }

    fun solution_speed(arr: IntArray): Double {
        var sum = 0
        // 배열을 한 번 순회하며 합계를 계산
        for (num in arr) {
            sum += num
        }
        // 합계를 배열의 길이로 나누어 평균 계산
        return sum.toDouble() / arr.size
    }
}
