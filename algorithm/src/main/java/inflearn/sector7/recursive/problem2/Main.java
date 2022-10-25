package inflearn.sector7.recursive.problem2;

/**
 * 재귀함수를 이용한 2진수 출력
 * 10 진수 N이 입력되면
 * 2진수로 변환하여 출력하는 프로그램 작성
 * 단, 재귀함수를 사용할 것
 *
 * input : 11
 * output : 1011
 */
public class Main {

    public void DFS(int n) {
        if (n == 0) return;
        else {
            DFS(n / 2);
            System.out.print(n % 2 + " ");
        }
    }


    public static void main(String[] args) {
        Main T = new Main();
        T.DFS(11);
    }
}
