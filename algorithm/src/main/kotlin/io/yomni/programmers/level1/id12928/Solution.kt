package io.yomni.programmers.level1.id12928

// https://school.programmers.co.kr/learn/courses/30/lessons/12928 - 약수의 합
class Solution {
    fun solution(n: Int): Int {
        var total = 0

        // 1부터 n의 제곱근까지 반복
        for (i in 1..Math.sqrt(n.toDouble()).toInt()) {
            // i가 n의 약수인지 확인
            if (n % i == 0) {
                total += i // i는 약수이므로 더함

                // 약수 쌍 추가 (서로 다를 경우)
                if (i != n / i) {
                    total += n / i // n/i도 약수
                }
            }
        }

        return total
    }
}
