package inflearn.string.section9;

import java.util.Scanner;

/**
 * 숫자만 추출
 * 
 * 설명
 * 문자와 숫자가 섞여있는 문자열이 주어지면 그 중 숫자만 추출하여 그 순서대로 자연수를 만듭니다.
 * 만약 “tge0a1h205er”에서 숫자만 추출하면 0, 1, 2, 0, 5이고 이것을 자연수를 만들면 1205이 됩니다.
 * 추출하여 만들어지는 자연수는 100,000,000을 넘지 않습니다.
 * 
 * 입력
 * 첫 줄에 숫자가 섞인 문자열이 주어집니다. 문자열의 길이는 100을 넘지 않습니다.
 * 
 * 출력
 * 첫 줄에 자연수를 출력합니다.
 * 
 * input : g0en2T0s8eSoft
 * ouput : 208
 */
public class Main {
    public Integer solution(String inputStr) {
        // My Answer
        String digitStr = inputStr.replaceAll("\\D", "");
        return Integer.parseInt(digitStr);

        // Lecture Answer
        /*String answer = "";
        for (char x : inputStr.toCharArray()) {
            if(Character.isDigit(x)){
                answer += x;
            }
        }
        return Integer.parseInt(answer);*/
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        String str = kb.nextLine();
//        String str = "g0en2T0s8eSoft";
        System.out.println(T.solution(str));
    }
}