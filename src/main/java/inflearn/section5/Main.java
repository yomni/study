package inflearn.section5;

import java.util.Scanner;

/**
 * 특정 문자 뒤집기
 * <p>
 * 설명
 * 영어 알파벳과 특수문자로 구성된 문자열이 주어지면 영어 알파벳만 뒤집고,
 * 특수문자는 자기 자리에 그대로 있는 문자열을 만들어 출력하는 프로그램을 작성하세요.
 * <p>
 * 입력
 * 첫 줄에 길이가 100을 넘지 않는 문자열이 주어집니다.
 * <p>
 * 출력
 * 첫 줄에 알파벳만 뒤집힌 문자열을 출력합니다.
 * <p>
 * input : a#b!GE*T@S ||| output : S#T!EG*b@a
 */
public class Main {

    // 특정 문자 뒤집기
    public String solution(String inputStr) {
        String answerStr = "";
        // 1. inputStr to char array, init
        char[] chars = inputStr.toCharArray();
        Integer lt = 0;
        Integer rt = inputStr.length() - 1;
        while (lt < rt) {
            if (!Character.isAlphabetic(chars[lt])) lt++;
            else if (!Character.isAlphabetic(chars[rt])) rt--;
            else {
                char tmp = chars[lt];
                chars[lt] = chars[rt];
                chars[rt] = tmp;

                lt++;
                rt--;
            }
        }
        answerStr = String.valueOf(chars);
        return answerStr;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        String str = kb.next();
        System.out.println(T.solution(str));
    }
}