package inflearn.slidingWindow.section4;

import java.util.Scanner;

/**
 * 4. 연속 부분수열
 *
 * 설명
 * N개의 수로 이루어진 수열이 주어집니다.
 * 이 수열에서 연속부분수열의 합이 특정숫자 M이 되는 경우가 몇 번 있는지 구하는 프로그램을 작성하세요.
 * 만약 N=8, M=6이고 수열이 다음과 같다면
 * 1 2 1 3 1 1 1 2
 * 합이 6이 되는 연속부분수열은 {2, 1, 3}, {1, 3, 1, 1}, {3, 1, 1, 1}로 총 3가지입니다.
 *
 * 입력
 * 첫째 줄에 N(1≤N≤100,000), M(1≤M≤100,000,000)이 주어진다.
 * 수열의 원소값은 1,000을 넘지 않는 자연수이다.
 *
 * 출력
 * 첫째 줄에 경우의 수를 출력한다.
 *
 * input :
 * 8 6
 * 1 2 1 3 1 1 1 2
 *
 * output : 3
 */
public class Main {
    public int solution(int n, int targetNum, int[] arr) {
        int answer = 0;
        int sum = 0;
        int p = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum > targetNum) {
                // sum이 targetNum 보다 작거나 같아질때 까지 줄여야한다.
                while (sum > targetNum) {
                    sum -= arr[p++];
                    if (sum == targetNum) answer++;
                    else if(sum < targetNum) break;
                }
            } else if (sum == targetNum) {
                answer++;
                sum -= arr[p++];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int targetNum = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(main.solution(n, targetNum, arr));
    }
}