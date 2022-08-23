package inflearn.sector7.recursive.problem5;

/*
 * 이진 트리 순회(깊이 우선 탐색)
 * 전위 : 부 - 왼 - 오
 * 중위 : 왼 - 부 - 오
 * 후위 : 왼 - 오 - 부
 *
 * 전위 순회 출력 : 1 2 4 5 3 6 7
 * 중위 순회 출력 : 4 2 5 1 6 3 7
 * 후위 순회 출력 : 4 5 2 6 7 3 1
 */

class Node {
  int data;
  Node lt, rt;

  public Node(int data) {
    this.data = data;
    lt = rt = null;
  }
}

public class Main {
  Node root;

  public void DFS(Node root) {
    if(root == null) return;
    else {
      // 전위 logic
      DFS(root.lt);
      // 중위 logic
      DFS(root.rt);
      // 후위 logic
      System.out.print(root.data + " ");
    }
  }
  public static void main(String[] args) {
    Main tree = new Main();
    tree.root = new Node(1);

    tree.root.lt = new Node(2);
    tree.root.rt = new Node(3);

    tree.root.lt.lt = new Node(4);
    tree.root.lt.rt = new Node(5);
    tree.root.rt.lt = new Node(6);
    tree.root.rt.rt = new Node(7);

    tree.DFS(tree.root);
  }
}
