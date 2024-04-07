import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m, n, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j)-'0';
            }
        }
        ans = Integer.MAX_VALUE;

        bfs();

        System.out.println(ans);
    }

    private static void bfs() {
        Deque<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        // 시작지는 항상 0이다.
        queue.addFirst(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            int currCnt = curr[2];

            // 기저
            if (currX == n-1 && currY == m-1) {
                ans = Math.min(ans, currCnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        queue.addFirst(new int[]{nx, ny, currCnt});
                        visited[nx][ny] = true;
                    } else if (map[nx][ny] == 1){
                        queue.add(new int[]{nx, ny, currCnt+1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}