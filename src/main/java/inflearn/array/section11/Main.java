package inflearn.array.section11;

import java.util.Scanner;

public class Main {
    public int solution(int n, int[][] a) {
        int answer = 0, max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= 5; k++) {
                    if (a[i][k] == a[j][k]) {
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt > max) {
                max = cnt;
                answer = i;
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

        Integer n = kb.nextInt();
        int[][] a = new int[n + 1][6];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 5; j++) {
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
        System.out.println(T.solution(n, a));
    }
}