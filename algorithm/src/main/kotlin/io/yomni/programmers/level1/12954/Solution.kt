package io.yomni.programmers.level1.`12954`

// https://school.programmers.co.kr/learn/courses/30/lessons/12954 - x만큼 간격이 있는 n개의 숫자
class Solution {
    fun solution(x: Int, n: Int): List<Long> {
        return List(n) { (it + 1) * x.toLong() }
    }
}
