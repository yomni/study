package inflearn.section1;

import java.util.Scanner;

public class Main {

    public int solution(String str, char t) {
        str = str.toUpperCase();
        t = Character.toUpperCase(t);
        char finalT = t;

        return (int) str.chars().filter(i -> i == finalT).count();
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        // sample
//        String str = "Computercooler";
//        char c = 'c';

        String str = kb.next();
        char c = kb.next().charAt(0);

        System.out.println(T.solution(str, c));
    }
}