import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, h_x, h_y, e_x, e_y, result = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][][] visited; // 벽을 안 뚫고 뚫고
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // 홍익
        h_x = Integer.parseInt(st.nextToken());
        h_y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // 탈출구
        e_x = Integer.parseInt(st.nextToken());
        e_y = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2]; // 0 -> 벽 아직 안 뚫음, 1 -> 벽 뚫음

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 탐색 시작
        int result = bfs(h_x-1, h_y-1);

        System.out.println(result);

    }

    private static int bfs(int startX, int startY) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0, 0));
        visited[startX][startY][0] = true; // 벽 안 뚫음.

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == e_x-1 && point.y == e_y-1) {
                result = Math.min(result, point.cnt);
                return result;
            }

            for (int i = 0; i < 4; i++) {
                int newX = point.x + dx[i];
                int newY = point.y + dy[i];
                int isBreak = point.isBreak;
                int count = point.cnt; // 거리
                if (newX >= 0 && newY >= 0 && newX < n && newY < m && !visited[newX][newY][isBreak]) {
                    // 벽 아니면
                    if (map[newX][newY] == 0) {
                        visited[newX][newY][isBreak] = true;
                        queue.offer(new Point(newX, newY, count + 1, isBreak));
                    }
                    // 벽인데 아직 안 부쉈으면
                    if (map[newX][newY] == 1 && isBreak == 0) {
                        visited[newX][newY][1] = true;
                        queue.offer(new Point(newX, newY, count + 1, 1));
                    }
                }
            }
        }
        return -1;

    }


    static class Point {
        int x, y, cnt;
        int isBreak;
        public Point(int x, int y, int cnt, int isBreak) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isBreak = isBreak; // 0이면 안 부숨
        }
    }

}
