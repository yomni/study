package inflearn.sector2.array.problem9;

import java.util.Scanner;

/**
 * 9. 격자판 최대합
 *
 * 설명
 * 5*5 격자판에 아래롸 같이 숫자가 적혀있습니다.
 * N*N의 격자판이 주어지면 각 행의 합, 각 열의 합, 두 대각선의 합 중 가 장 큰 합을 출력합니다.
 *
 * 입력
 * 첫 줄에 자연수 N이 주어진다.(2<=N<=50)
 * 두 번째 줄부터 N줄에 걸쳐 각 줄에 N개의 자연수가 주어진다. 각 자연수는 100을 넘지 않는다.
 *
 * 출력
 * 최대합을 출력합니다.
 *
 * input :
 * 5
 * 10 13 10 12 15
 * 12 39 30 23 11
 * 11 25 50 53 15
 * 19 27 29 37 27
 * 19 13 30 13 19
 *
 * output : 155
 */
public class Main {
    private int solution(int n, int[][] a) {
        int answer = Integer.MIN_VALUE;

        int mainDiagonalSum = 0;
        int oppositeDiagonalSum = 0;
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += a[i][j];
                colSum += a[j][i];
            }
            int subMax = Math.max(rowSum, colSum);
            if (subMax > answer) {
                answer = subMax;
            }

            mainDiagonalSum += a[i][i];
            oppositeDiagonalSum += a[i][n - i - 1];
        }
        int max = Math.max(mainDiagonalSum, oppositeDiagonalSum);
        if (max > answer) {
            answer = max;
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

        Integer n = kb.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = kb.nextInt();
            }
        }

//        int n = 5;
//
//        int[][] a = new int[][]{
//                {10, 13, 10, 12, 15},
//                {12, 39, 30, 23, 11},
//                {11, 25, 50, 53, 15},
//                {19, 27, 29, 37, 27},
//                {19, 13, 30, 13, 19}
//        };


        System.out.println(T.solution(n, a));
    }
}