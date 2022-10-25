package inflearn.sector5.stack.problem1;

import java.util.Scanner;
import java.util.Stack;

/**
 * 1. 올바른 괄호
 *
 * 설명
 * 괄호가 입력되면 올바른 괄호이면 “YES", 올바르지 않으면 ”NO"를 출력합니다.
 * (())() 이것은 괄호의 쌍이 올바르게 위치하는 거지만, (()()))은 올바른 괄호가 아니다.
 *
 * 입력
 * 첫 번째 줄에 괄호 문자열이 입력됩니다. 문자열의 최대 길이는 30이다.
 *
 * 출력
 * 첫 번째 줄에 YES, NO를 출력한다.
 *
 * input : (()(()))(()
 *
 * output : NO
 */
public class Main {
    public String solution(String brackets) {
        String answer = "NO";
        Stack<Character> stack = new Stack<>();
        for (char b : brackets.toCharArray()) {
            if(b == '(') stack.push(b);
            else {
                if(stack.isEmpty()) return answer;
                else stack.pop();
            }
        }
        if(stack.isEmpty()) answer = "YES";
        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);


        String brackets = sc.next();
        System.out.println(main.solution(brackets));
    }

}