package programmers.level1.크기가작은_부분문자열;

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        long part = Long.valueOf(p);
        // 부분 문자열 정하기
        for (int i = 0; i <= t.length() - p.length(); i++) {
            long target = Long.valueOf(t.substring(i, i + p.length()));
            if (target <= part) {
                answer++;
            }
        }

        return answer;
    }
}