package io.yomni.programmers.level1.id12937

// https://school.programmers.co.kr/learn/courses/30/lessons/12937 - 짝수와 홀수

class Solution {
    fun solution(num: Int): String {
        return if (num % 2 == 0) {
            "Even"
        } else {
            "Odd"
        }
    }
}
