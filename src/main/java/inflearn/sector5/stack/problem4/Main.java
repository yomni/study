package inflearn.sector5.stack.problem4;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public int solution(String postfix) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (char x : postfix.toCharArray()) {
            if (Character.isDigit(x)) stack.push(x - 48);
            else {
                int rt = stack.pop();
                int lt = stack.pop();
                if (x == '+') stack.push(lt + rt);
                else if (x == '-') stack.push(lt - rt);
                else if (x == '*') stack.push(lt * rt);
                else if (x == '/') stack.push(lt / rt);
            }
        }
        answer = stack.get(0);
        return answer;
    }

    public int mySolution(String postfix) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (char x : postfix.toCharArray()) {
            if (isInteger(String.valueOf(x))) {
                stack.push(Integer.parseInt(String.valueOf(x)));
            } else {
                int preNum = stack.pop();
                int postNum = stack.pop();
                int result = 0;
                switch (x) {
                    case '+':
                        result = postNum + preNum;
                        break;
                    case '-':
                        result = postNum - preNum;
                        break;
                    case '*':
                        result = postNum * preNum;
                        break;
                    case '/':
                        result = postNum / preNum;
                        break;
                }
                stack.push(result);
            }
        }
        answer = stack.peek();
        return answer;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);


        String postfix = sc.next();

        System.out.println(main.solution(postfix));
    }
}