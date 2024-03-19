import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, numberOfArea = 0, maxArea = 0;
    static int[][] map ;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    numberOfArea++;
                    int ans = bfs(i, j);
                    maxArea = Math.max(maxArea, ans);
                }
            }
        }


        System.out.println(numberOfArea);
        System.out.print(maxArea);

    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int size = 1;

        visited[x][y] = true;
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] numArr = queue.poll();
            int a = numArr[0];
            int b = numArr[1];
            for (int i = 0; i < 4; i++) {
                int nx = a + dx[i];
                int ny = b + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    // size를 누적시키고
                    queue.add(new int[]{nx, ny});
                    size += bfs(nx, ny);
                }
            }

        }
        return size;
    }

    static class Node {
        private int x, y, size;

        public Node(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

    }
}