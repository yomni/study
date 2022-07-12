package inflearn.sector6.sort.problem7;

import java.util.*;

/**
 * 7. 좌표 정렬
 *
 * 설명
 * N개의 평면상의 좌표(x, y)가 주어지면 모든 좌표를 오름차순으로 정렬하는 프로그램을 작성하세요.
 * 정렬기준은 먼저 x값의 의해서 정렬하고, x값이 같을 경우 y값에 의해 정렬합니다.
 *
 * 입력
 * 첫째 줄에 좌표의 개수인 N(3<=N<=100,000)이 주어집니다.
 * 두 번째 줄부터 N개의 좌표가 x, y 순으로 주어집니다. x, y값은 양수만 입력됩니다.
 *
 * 출력
 * N개의 좌표를 정렬하여 출력하세요.
 *
 * input :
 * 5
 * 2 7
 * 1 3
 * 1 2
 * 2 5
 * 3 6
 *
 * output :
 * 1 2
 * 1 3
 * 2 5
 * 2 7
 * 3 6
 */
public class Main {
    public List<Point> solution(int n, List<Point> pList) {
        Collections.sort(pList);
        return pList;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Point> pList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            pList.add(new Point(x, y));
        }
        for (Point p : main.solution(n, pList)) {
            System.out.println(p.getX() + " " + p.getY());
        }
    }

    private static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.getX() > o.getX()) {
                return 1;
            } else if (this.getX() < o.getX()) {
                return -1;
            } else if (this.getX() == o.getX()) {
                if (this.getY() >= o.getY()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        }
    }
}
