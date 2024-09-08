import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans = 0;
    static int[][] mountain;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mountain = new int[n][m];
        visited = new boolean[n][m];

        // 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mountain[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조건에 맞는 것 bfs메서드 실행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && mountain[i][j] > 0) {
                    // 산봉우리 존재
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);

    }

    private static void bfs(int x, int y) {
        Queue<Square> queue = new LinkedList<>();
        queue.offer(new Square(x, y, mountain[x][y]));
        visited[x][y] = true;
        boolean isPeak = true;

        while(!queue.isEmpty()) {
            Square current = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    int newHeight = mountain[nx][ny];
                    if (newHeight > current.height) {
                        isPeak = false;
                    }
                    if (!visited[nx][ny] && newHeight == current.height) {
                        visited[nx][ny] = true;
                        queue.offer(new Square(nx, ny, newHeight));
                    }
                }
            }
        }
        if (isPeak) {
            ans++;
        }
    }

    static class Square {
        private int x, y, height;

        public Square(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

    }
}