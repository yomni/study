package inflearn.sector5.stack.problem2;

import java.util.Scanner;
import java.util.Stack;

/**
 * 2. 괄호문자제거
 *
 * 설명
 * 입력된 문자열에서 소괄호 ( ) 사이에 존재하는 모든 문자를 제거하고 남은 문자만 출력하는 프로그램을 작성하세요.
 *
 * 입력
 * 첫 줄에 문자열이 주어진다. 문자열의 길이는 100을 넘지 않는다.
 *
 * 출력
 * 남은 문자만 출력한다.
 *
 * input : (A(BC)D)EF(G(H)(IJ)K)LM(N)
 *
 * output : EFLM
 */
public class Main {
    public String solution(String brackets) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char b : brackets.toCharArray()) {
            if (b == '(') {
                stack.push(b);
            } else if (b == ')') {
                stack.pop();
            } else if (stack.isEmpty()) {
                sb.append(b);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);


        String brackets = sc.next();
        System.out.println(main.solution(brackets));
    }

}