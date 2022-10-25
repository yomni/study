package inflearn.sector7.recursive.problem6;

/*
 * 부분 집합 구하기 (DFS)
 * 자연수 N이 주어지면 1 부터 N까지의 원소를 갖는 집합의 부분집합을 모두 출력하는 프로그램을 작성하세요
 *
 * input : 3
 * output :
 * 1 2 3
 * 1 2
 * 1 3
 * 1
 * 2 3
 * 2
 * 3
 *
 */
public class Main {
  static int n;
  static int[] ch;

  public void DFS(int L) {
    if (L == n + 1) {
      for (int i = 1; i < ch.length; i++) {
        if (ch[i] == 1) {
          System.out.print(i + " ");
        }
      }
      System.out.println();
    } else {
      ch[L] = 1;
      DFS(L + 1);
      ch[L] = 0;
      DFS(L + 1);
    }
  }

  public static void main(String[] args) {
    Main T = new Main();
    n = 3;
    ch = new int[n + 1];
    T.DFS(1);
  }
}
