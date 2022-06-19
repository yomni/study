package inflearn.sector3.slidingWindow.problem5;

import java.util.Scanner;

/**
 * 5. 연속된 자연수의 합
 *
 * 설명
 * N입력으로 양의 정수 N이 입력되면 2개 이상의 연속된 자연수의 합으로 정수 N을 표현하는 방법의 가짓수를 출력하는 프로그램을 작성하세요.
 * 만약 N = 15이면
 * 7 + 8 = 15
 * 4 + 5 + 6 = 15
 * 1 + 2 + 3 + 4 + 5 = 15
 * 와 같이 총 3가지의 경우가 존재한다.
 *
 * 입력
 * 첫 번째 줄에 양의 정수 N(7 <= N < 1000)이 주어집니다.
 *
 * 출력
 * 첫 줄에 총 경우수를 출력합니다.
 *
 * input : 15
 * output : 3
 */
public class Main {
    /**
     * sliding window 로 해결
     * @param targetNum
     * @return
     */
    public int solution(int targetNum) {
        int answer = 0;
        int sum = 0;
        int p = 0;
        int n = (targetNum / 2) + 1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == targetNum) answer++;
            while (sum > targetNum) {
                sum -= arr[p++];
                if (sum == targetNum) answer++;
            }
        }
        return answer;
    }

    /**
     * 수학적 방법으로 해결
     * @param targetNum
     * @return
     */
    public int solunion2(int targetNum) {
        int answer = 0;
        int cnt = 1;
        targetNum--;

        while (targetNum > 0) {
            cnt++;
            targetNum -= cnt;
            if(targetNum % cnt == 0) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(main.solunion2(n));
    }
}