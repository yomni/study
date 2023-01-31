package programmers.level0.종이자르기;

class Solution {
    public int solution(int M, int N) {
        int answer = 0;
        int map[][] = new int[M][N];

        // base case
        for (int i = 0; i < M; i++) {
            map[i][0] = i;
        }

        // DP
        for (int i = 0; i < M; i++) {
            for (int j = 1; j < N; j++) {
                map[i][j] = map[i][j - 1] + i + 1;
            }
        }

        answer = map[M - 1][N - 1];
        return answer;
    }

    public int solution2(int M, int N) {
        return M * N - 1;
    }
}