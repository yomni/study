package inflearn.sector7.recursive.problem3;

/**
 * 팩토리얼
 *
 * 자연수 N이 입력되면 N!를 구하ㅡㄴ 프로그램 작성
 * 5! = 5 * 4 * 3 * 2 * 1 = 120
 *
 * input : 5
 * output : 120
 */
public class Main {

    public int factorial(int n) {
        if (n == 1) return 1;
        else return n * factorial(n - 1);

    }


    public static void main(String[] args) {
        Main T = new Main();
        System.out.println(T.factorial(5));
    }
}
