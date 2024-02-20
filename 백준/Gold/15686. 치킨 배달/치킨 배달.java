import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* logic
    1. 치킨집을 최대 m개 고르는 것이라 했으므로 1 ~ m개를 선택하는 조합으로 문제를 해결할 수 있다.
    2. */
public class Main {
    static int n, m;
    static int[][] map;
    static ArrayList<Point> chicken, home;
    static Point[] selectedChicken;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        chicken = new ArrayList<>();
        home = new ArrayList<>();
        selectedChicken = new Point[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    home.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        selectChicken(0, 0);
        System.out.println(answer);

    }

    // 치킨집 고르는 메서드
    private static void selectChicken(int idx, int cnt) {
        // 기저
        if (cnt == m) {
            calculateChickenDistance();
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            selectedChicken[cnt] = chicken.get(i);
            selectChicken(i+1, cnt+1);
        }
    }

    // 치킨 거리 구하는 메서드
    private static void calculateChickenDistance() {
        int totalDistance = 0;

        for (Point h : home) {
            int minDistance = Integer.MAX_VALUE;

            for (Point c : selectedChicken) {
                int distance = chickenDis(h.x, h.y, c.x, c.y);
                minDistance = Math.min(distance, minDistance);
            }
            totalDistance += minDistance;
        }
        answer = Math.min(totalDistance, answer);
    }

    // 치킨거리 구하는 메서드
    static int chickenDis(int x1, int y1, int x2, int y2) {
        return Math.abs(x2-x1) + Math.abs(y2-y1);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}