package inflearn.section7.recursive;

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
