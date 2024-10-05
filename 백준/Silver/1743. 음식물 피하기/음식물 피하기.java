import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] trashes;
    static boolean[][] visited;
    static int n, m, k, ans;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        trashes = new int[n][m];
        visited = new boolean[n][m];

        // 돌면서
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            trashes[x][y] = 1; // 쓰레기
        }

        ans = Integer.MIN_VALUE;
        // 가장 큰 쓰레기 탐지
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (trashes[i][j] == 1 && !visited[i][j]) {
                    int trashSize = bfs(i, j);
                    ans = Math.max(ans, trashSize);
                }
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int currTrash = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int currX = cur[0];
            int currY = cur[1];
            for (int i = 0; i < 4; i++) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];
                if (newX >= 0 && newY >= 0 && newX < n && newY < m) {
                    if (trashes[newX][newY] == 1 && !visited[newX][newY]) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                        currTrash++;
                    }
                }
            }

        }
        return currTrash;
    }

}
