package inflearn.sector7.recursive.problem4;

/*
 * 피보나치 수열
 * 앞의 2개의 수를 합하여 다음 숫자가 되는 수열
 * 입력은 피보나치 수열의 총 항의 수이다.
 *
 * input : 10
 * output : 1 1 2 3 5 8 13 21 34 55
 */
public class Main {
  public int DFS(int n) {
    if (n == 1 || n == 2) return 1;
    else return DFS(n - 1) + DFS(n - 2);
  }

  public static void main(String[] args) {
    Main T = new Main();
    System.out.println(T.DFS(10));
  }
}
