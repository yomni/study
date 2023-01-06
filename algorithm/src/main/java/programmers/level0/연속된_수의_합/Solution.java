package programmers.level0.연속된_수의_합;

import java.util.stream.IntStream;

class Solution {
    public int[] solution(int num, int total) {
        int candidate = Integer.MAX_VALUE;
        int rightLimit = 0;
        int leftLimit = rightLimit - num;
        while (total != candidate) {
            candidate = IntStream.range(leftLimit, rightLimit).sum();
            if (candidate != total) {
                leftLimit++;
                rightLimit++;
            }
        }
        return IntStream.range(leftLimit, rightLimit).toArray();
    }
}
