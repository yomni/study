package inflearn.section10;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 가장 짧은 문자거리

 * 설명
 * 한 개의 문자열 s와 문자 t가 주어지면 문자열 s의 각 문자가 문자 t와 떨어진 최소거리를 출력하는 프로그램을 작성하세요.

 * 입력
 * 첫 번째 줄에 문자열 s와 문자 t가 주어진다. 문자열과 문자는 소문자로만 주어집니다.
 * 문자열의 길이는 100을 넘지 않는다.

 * 출력
 * 첫 번째 줄에 각 문자열 s의 각 문자가 문자 t와 떨어진 거리를 순서대로 출력한다.

 * input : teachermode e
 * ouput : 1 0 1 2 1 0 1 2 2 1 0
 */
public class Main {
    public Integer[] solution(String inputStr, Character ch) {
        Integer[] answers = new Integer[inputStr.length()];
        // 정방향
        Integer answer = 200;
        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) == ch) {
                answer = 0;
            } else {
                answer++;
            }
            answers[i] = answer;
        }

        // 역방향
        answer = 200;
        for (int i = inputStr.length() - 1; i >= 0; i--) {
            if (inputStr.charAt(i) == ch) {
                answer = 0;
            } else {
                answer++;
                answers[i] = Math.min(answer, answers[i]);
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        String str = kb.next();
        Character ch = kb.next().charAt(0);

//        String str = "eochjgoekghlakegh";
//        Character ch = 'h';
//        1 0 1 2 1 0 1 2 2 1 0
        Integer[] answers = T.solution(str, ch);
        Arrays.stream(answers).forEach(e -> System.out.print(e + " "));
    }
}