package inflearn.array.section13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 큰 수 출력하기
 *
 * 설명
 * N개의 정수를 입력받아, 자신의 바로 앞 수보다 큰 수만 출력하는 프로그램을 작성하세요.
 * (첫 번째 수는 무조건 출력한다)
 *
 * 입력
 * 첫 줄에 자연수 N(1<=N<=100)이 주어지고, 그 다음 줄에 N개의 정수가 입력된다.
 *
 * 출력
 * 자신의 바로 앞 수보다 큰 수만 한 줄로 출력한다.
 *
 * input :
 * 6
 * 7 3 9 5 6 12
 *
 * output :
 * 7 9 6 12
 */
public class Main {
    private int[] solution(int num, int[] intArr) {
        List<Integer> list = new ArrayList<>();
        list.add(intArr[0]);

        for (int i = 1; i < num; i++) {
            if (intArr[i] > intArr[i - 1]) list.add(intArr[i]);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }

//        int n = 6;
//        int[] arr = {7, 3, 9, 5, 6, 12};
        for(int x : T.solution(n, arr)) {
            System.out.print(x + " ");
        }
    }
}