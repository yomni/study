package inflearn.array.section4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 4. 피보나치 수열
 *
 * 설명
 * 1) 피보나치 수열을 출력한다. 피보나치 수열이란 앞의 2개의 수를 합하여 다음 숫자가 되는 수열이다.
 * 2) 입력은 피보나치 수열의 총 항의 수 이다. 만약 7이 입력되면 1 1 2 3 5 8 13을 출력하면 된다.
 *
 * 입력
 * 첫 줄에 총 항수 N(3<=N<=45)이 입력된다.
 *
 * 출력
 * 첫 줄에 피보나치 수열을 출력합니다.
 *
 * input : 10
 * output : 1 1 2 3 5 8 13 21 34 55
 */
public class Main {
    private List<Integer> solution(int n) {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(1);
        for (int i = 2; i < n; i++) {
            answer.add(answer.get(i - 2) + answer.get(i - 1));
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

        int n = kb.nextInt();

//        int n = 10;

        for (Integer e : T.solution(n)) {
            System.out.print(e + " ");
        }
    }
}