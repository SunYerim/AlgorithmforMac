import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, l, r, days;
    static boolean isMove = false;
    static int[][] map;
    static boolean[][] opened;
    static ArrayList<Point> union;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        //opened = new boolean[n*n][n*n]; // 국경 연결관계 나타내기 위함 -> 매 턴 초기화

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        days = 0;

        move();

        System.out.println(days);


    }

    private static void move() {
        // 인구이동이 가능할때까지
        while (true) {
            isMove = false;
            // boolean배열 매턴 갱신
            opened = new boolean[n][n];

            // 매 칸에서 방문하지 않으면 bfs 실행
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!opened[i][j])
                        bfs(i, j);
                }
            }
            // 국경간 이동이 안되면
            if (!isMove)
                break;
            // isMove -> true 처리 되었으면
            else days++;
        }

    }

    // bfs
    private static void bfs(int x, int y) {
        // 탐색
        Queue<Point> queue = new LinkedList<>();
        union = new ArrayList<>();
        queue.offer(new Point(x, y));
        opened[x][y] = true;
        union.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            x = p.getX();
            y = p.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!opened[nx][ny] && Math.abs(map[nx][ny] - map[x][y]) >= l && Math.abs(map[nx][ny] - map[x][y]) <= r) {
                        isMove = true;
                        opened[nx][ny] = true;
                        union.add(new Point(nx, ny)); // 연합국 확인 -> 나중에 map갱신에 필요함
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }

        // bfs 끝나면 인구 이동결과 토대로 맵을 갱신
        int total = 0;
        // 더하고
        for (int i = 0; i < union.size(); i++) {
            Point p = union.get(i);
            total += map[p.x][p.y];
        }

        for (int i = 0; i < union.size(); i++) {
            Point p = union.get(i);
            map[p.x][p.y]= total / union.size();
        }
    }

    static class Point {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}