package inflearn.sector4.hash.problem4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 4. 모든 아나그램 찾기
 *
 * 설명
 * S문자열에서 T문자열과 아나그램이 되는 S의 부분문자열의 개수를 구하는 프로그램을 작성하세요.
 * 아나그램 판별시 대소문자가 구분됩니다. 부분문자열은 연속된 문자열이어야 합니다.
 *
 * 입력
 * 첫 줄에 첫 번째 S문자열이 입력되고, 두 번째 줄에 T문자열이 입력됩니다.
 * S문자열의 길이는 10,000을 넘지 않으며, T문자열은 S문자열보다 길이가 작거나 같습니다.
 *
 * 출력
 * S단어에 T문자열과 아나그램이 되는 부분문자열의 개수를 출력합니다.
 *
 * input :
 * bacaAacba
 * abc
 *
 * output : 3
 */
public class Main {
    public int solution(String S, String T) {
        int answer = 0;
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> slideMap = new HashMap<>();
        int lt = 0;
        for (int i = 0; i < T.length(); i++) {
            char key = T.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int i = 0; i < S.length(); i++) {

            char rtKey = S.charAt(i);
            int rtValue = slideMap.getOrDefault(rtKey, 0) + 1;
            if (i < T.length()) {
                slideMap.put(rtKey, rtValue);
            } else {
                slideMap.put(rtKey, rtValue);

                char ltKey = S.charAt(lt);
                int ltValue = slideMap.getOrDefault(ltKey, 0) - 1;
                if (ltValue < 1) slideMap.remove(ltKey);
                else slideMap.put(ltKey, ltValue);
                lt++;
            }
            if (slideMap.equals(map)) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        String T = sc.next();

        System.out.println(main.solution(S, T));
    }
}