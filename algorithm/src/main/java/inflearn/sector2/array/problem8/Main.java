package inflearn.sector2.array.problem8;

import java.util.Scanner;

/**
 * 8. 등수구하기
 *
 * 설명
 * N명의 학생의 국어점수가 입력되면 각 학생의 등수를 입력된 순서대로 출력하는 프로그램을 작성하세요.
 * 같은 점수가 입력될 경우 높은 등수로 동일 처리한다.
 * 즉 가장 높은 점수가 92점인데 92점이 3명 존재하면 1등이 3명이고 그 다음 학생은 4등이 된다.
 *
 * 입력
 * 첫 줄에 N(3<=N<=100)이 입력되고, 두 번째 줄에 국어점수를 의미하는 N개의 정수가 입력된다.
 *
 * 출력
 * 입력된 순서대로 등수를 출력한다.
 *
 * input :
 * 5
 * 87 89 92 100 76
 *
 * output :
 * 4 3 2 1 5
 */
public class Main {
    private int[] solution(int n, int[] a) {
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < n; j++) {
                if (a[j] > a[i]) cnt++;
            }
            answer[i] = cnt;
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

        Integer n = kb.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = kb.nextInt();
        }

//        int n = 9;
//        int[] a = new int[]{
//            32, 55, 62, 20, 250, 370, 200, 30, 100
//        };


        for ( int x : T.solution(n, a) ){
            System.out.print(x + " ");
        }
    }
}