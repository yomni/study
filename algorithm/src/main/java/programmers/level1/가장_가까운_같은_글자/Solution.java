package programmers.level1.가장_가까운_같은_글자;

import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Arrays.fill(answer, -1);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = 0;

            for (int j = i - 1; j >= 0; j--) {
                count++;
                if (c == s.charAt(j)) {
                    answer[i] = count;
                    break;
                }
            }
        }
        return answer;
    }
}