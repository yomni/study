package inflearn.sector7.recursive.problem1;

/**
 * 스택 프레임(재귀함수) - 피보나치
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.DFS(3));
    }

    private int DFS(int n) {
        if(n == 0) return 1;
        else return n + DFS(n - 1);
    }
}
