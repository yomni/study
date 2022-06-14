package inflearn.slidingWindow.section3;

import java.util.Scanner;

public class Main {
    public int solution(int n, int k, int[] arr) {
        int answer = 0, sum = 0;
        for (int i = 0; i < k; i++) sum += arr[i];
        answer = sum;

        for (int i = k; i < n; i++) {
            sum += (arr[i] - arr[i - k]);
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

//        int n = 10;
//        int k = 3;
//        int[] arr = {12, 15, 11, 20, 25, 10, 20, 19, 13, 15};

        System.out.println(main.solution(n, k, arr));
    }
}