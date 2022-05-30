package inflearn.section4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 단어 뒤집기
 * 
 * 설명
 * N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램을 작성하세요.
 * 
 * 입력
 * 첫 줄에 자연수 N(3<=N<=20)이 주어집니다.
 * 두 번째 줄부터 N개의 단어가 각 줄에 하나씩 주어집니다. 단어는 영어 알파벳으로만 구성되어 있습니다.
 * 
 * 출력
 * N개의 단어를 입력된 순서대로 한 줄에 하나씩 뒤집어서 출력합니다.
 *
 * input :
 * 3
 * good
 * Time
 * Big
 *
 * output :
 * doog
 * emiT
 * giB
 */
public class Main {

    // 단어 뒤집기
    public List<String> solution(Integer i, String[] arrStr) {
        List<String> answerList = new ArrayList<>();
        Arrays.stream(arrStr).forEach(str -> {
            StringBuffer sb = new StringBuffer(str);
            answerList.add(sb.reverse().toString());
        });
        return answerList;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);

        Integer inputInt = kb.nextInt();
        String[] arrStr = new String[inputInt];
        for (int i = 0; i < inputInt; i++) {
            arrStr[i] = kb.next();
        }
        for (String x : T.solution(inputInt, arrStr)) {
            System.out.println(x);
        }
    }
}