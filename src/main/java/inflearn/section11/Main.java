package inflearn.section11;

import java.util.Scanner;

/**
 * 문자열 압축
 *
 * 설명
 * 알파벳 대문자로 이루어진 문자열을 입력받아 같은 문자가 연속으로 반복되는 경우 반복되는
 * 문자 바로 오른쪽에 반복 횟수를 표기하는 방법으로 문자열을 압축하는 프로그램을 작성하시오.
 * 단 반복횟수가 1인 경우 생략합니다.
 *
 * 입력
 * 첫 줄에 문자열이 주어진다. 문자열의 길이는 100을 넘지 않는다.
 *
 * 출력
 *
 * input1 : KKHSSSSSSSE
 * ouput1 : K2HS7E
 *
 * input2 : KSTTTSEEKFKKKDJJGG
 * ouput2 : KST3SE2KFK3DJ2G2
 */
public class Main {

    private String mySolution(String inputStr) {
        String answer = "";
        char preCh = inputStr.charAt(0);
        int compressCount = 1;
        answer += preCh;

        for (int i = 1; i < inputStr.length(); i++) {
            char currentCh = inputStr.charAt(i);
            if (preCh != currentCh) {
                if (compressCount > 1) answer += compressCount;
                answer += currentCh;
                preCh = currentCh;
                compressCount = 0;
            }
            compressCount++;
            if (i == (inputStr.length() - 1) && compressCount > 1) {
                answer += compressCount;
            }
        }
        return answer;
    }

    private String solution(String inputStr) {
        String answer = "";
        inputStr = inputStr + " ";
        int cnt = 1;
        for (int i = 0; i < inputStr.length() - 1; i++) {
            if (inputStr.charAt(i) == inputStr.charAt(i + 1)) cnt++;
            else {
                answer += inputStr.charAt(i);
                if (cnt > 1) answer += String.valueOf(cnt);
                cnt = 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Main T = new Main();

//        String str = kb.next();
//        String str = "KKHSSSSSSSE"; // K2HS7E
        String str = "KSTTTSEEKFKKKDJJGG"; // KST3SE2KFK3DJ2G2

        System.out.println(T.solution(str));
    }
}