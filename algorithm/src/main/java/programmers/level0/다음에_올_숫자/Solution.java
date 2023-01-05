package programmers.level0.다음에_올_숫자;

class Solution {
    public int solution(int[] common) {
        int answer = 0;

        int pre = common[1] - common[0];
        int post = common[2] - common[1];

        if (pre == post) {
            answer = common[common.length - 1] + post;
        } else {
            answer = common[common.length - 1] * common[2] / common[1];
        }

        return answer;
    }
}