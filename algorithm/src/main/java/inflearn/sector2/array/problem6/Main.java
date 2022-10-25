package inflearn.sector2.array.problem6;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 6. 뒤집은 소수
 *
 * 설명
 * N개의 자연수가 입력되면 각 자연수를 뒤집은 후 그 뒤집은 수가 소수이면 그 소수를 출력하는 프로그램을 작성하세요.
 * 예를 들어 32를 뒤집으면 23이고, 23은 소수이다. 그러면 23을 출력한다. 단 910를 뒤집으면 19로 숫자화 해야 한다.
 * 첫 자리부터의 연속된 0은 무시한다.
 *
 * 입력
 * 첫 줄에 자연수의 개수 N(3<=N<=100)이 주어지고, 그 다음 줄에 N개의 자연수가 주어진다.
 * 각 자연수의 크기는 100,000를 넘지 않는다.
 *
 * 출력
 * 첫 줄에 뒤집은 소수를 출력합니다. 출력순서는 입력된 순서대로 출력합니다.
 *
 * input :
 * 9
 * 32 55 62 20 250 370 200 30 100
 *
 * output :
 * 23 2 73 2 3
 */
public class Main {
    private List<Integer> solution(int n, int[] a) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int candidatorInt = Integer.parseInt(new StringBuilder(String.valueOf(a[i])).reverse().toString());
            if(BigInteger.valueOf(candidatorInt).isProbablePrime(10)) {
                answer.add(candidatorInt);
            }
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



        for(int x : T.solution(n, a)) {
            System.out.print(x + " ");
        }
    }
}