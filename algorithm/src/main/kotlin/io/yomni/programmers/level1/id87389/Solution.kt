package io.yomni.programmers.level1.id87389

// https://school.programmers.co.kr/learn/courses/30/lessons/87389 - 나머지가 1이 되는 수 찾기

class Solution {

    fun solution(n: Int): Int {
        var result = 1
        for (x in 2 until n) {
            if (n % x == 1) {
                result = x
                break
            }
        }
        return result
    }

    // 실행속도가 느림
    fun solution_short(n: Int): Int {
        return (1..n).first { n % it == 1 }
    }

    fun solution_origin(n: Int): Int {
        var result = 1
        for (x in 2 until n) {
            if (n % x == 1) {
                result = x
                break
            }
        }
        return result
    }
}
