package inflearn.array.section12;

import java.util.Scanner;

public class Main {
    // 12. 멘토링
    public int solution(int n, int m, int[][] a) {
        int answer = 0;
        int[][] b = new int[n][n]; // 멘티 - 멘토 그래프

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int mentorIndex = a[i][j] - 1;
                for (int k = j + 1; k < n; k++) {
                    int menteeIndex = a[i][k] - 1;
                    if (b[menteeIndex][mentorIndex] == 1) b[menteeIndex][mentorIndex] = -1;
                    if (b[mentorIndex][menteeIndex] != -1) {
                        b[mentorIndex][menteeIndex] = 1;
                        b[menteeIndex][mentorIndex] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] == 1) answer += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

        int n = kb.nextInt();
        int m = kb.nextInt();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = kb.nextInt();
            }
        }

//        int n = 5;
//        int[][] a = new int[][]{
//                {5, 3, 7, 2, 3},
//                {3, 7, 1, 6, 1},
//                {7, 2, 5, 3, 4},
//                {4, 3, 6, 4, 1},
//                {8, 7, 3, 5, 2}
//        };
        System.out.println(T.solution(n, m, a));
    }
}