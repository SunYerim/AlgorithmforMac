import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int W, H, answer = 0;
    static int[][] board;
    static boolean[][] visited;

    static int[] dy_even = {-1, -1, 0, 1, 1, 0};
    static int[] dx_even = {0, -1, 1, 0, -1, -1};
    static int[] dy_odd = {-1, -1, 0, 1, 1, 0};
    static int[] dx_odd = {0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H + 2][W + 2];
        visited = new boolean[H + 2][W + 2];

        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        for (int i = 0; i < H + 2; i++) {
            for (int j = 0; j < W + 2; j++) {
                if (board[i][j] == 1) {
                    int[] currDy = (i % 2 == 0) ? dy_even : dy_odd;
                    int[] currDx = (i % 2 == 0) ? dx_even : dx_odd;

                    for (int k = 0; k < 6; k++) {
                        int ny = i + currDy[k];
                        int nx = j + currDx[k];

                        if (ny >= 0 && ny < H + 2 && nx >= 0 && nx < W + 2 && visited[ny][nx]) {
                            answer++;
                        }
                    }
                }
            }
        }

        System.out.println(answer);

    }

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            int[] currDy = (curr.y % 2 == 0) ? dy_even : dy_odd;
            int[] currDx = (curr.y % 2 == 0) ? dx_even : dx_odd;

            for (int i = 0; i < 6; i++) {
                int ny = curr.y + currDy[i];
                int nx = curr.x + currDx[i];

                if (ny < 0 || ny >= H + 2 || nx < 0 || nx >= W + 2) {
                    continue;
                }

                if (board[ny][nx] == 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }

    static class Point {

        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
