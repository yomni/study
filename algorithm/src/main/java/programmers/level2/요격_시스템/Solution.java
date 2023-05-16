package programmers.level2.요격_시스템;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/181188?language=java
 */
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        int interceptors = 0;
        int prevEnd = -1;

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            if (start >= prevEnd) {
                interceptors++;
                prevEnd = end;
            }
        }

        return interceptors;
    }

}
