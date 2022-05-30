package inflearn.section6;

import java.util.Scanner;

/**
 * 중복문자제거
 * 
 * 설명
 * 소문자로 된 한개의 문자열이 입력되면 중복된 문자를 제거하고 출력하는 프로그램을 작성하세요.
 * 중복이 제거된 문자열의 각 문자는 원래 문자열의 순서를 유지합니다.
 * 
 * 입력
 * 첫 줄에 문자열이 입력됩니다. 문자열의 길이는 100을 넘지 않는다.
 * 
 * 출력
 * 첫 줄에 중복문자가 제거된 문자열을 출력합니다.
 * 
 * input : ksekkset
 * output : kset
 */
public class Main {

    // 특정 문자 뒤집기
    public String solution(String inputStr) {
        String answerStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            if (i == inputStr.indexOf(inputStr.charAt(i))) {
                answerStr += inputStr.charAt(i);
            }
        }
        return answerStr;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        String str = kb.next();
        System.out.println(T.solution(str));
    }
}