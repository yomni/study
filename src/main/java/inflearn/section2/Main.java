package inflearn.section2;

import java.util.Scanner;

public class Main {

    // 문장속 단어
    public String solution(String str) {
        String answer = "";
        String[] s = str.split(" ");
        int max = Integer.MIN_VALUE;
        for (String c : s) {
            if (c.length() > max) {
                max = c.length();
                answer = c;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        String str = kb.nextLine();
//        char c = kb.next().charAt(0);

        System.out.println(T.solution(str));
    }
}