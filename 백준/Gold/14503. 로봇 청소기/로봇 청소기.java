
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
    static int[] dy = {0, 1, 0, -1}; // 북, 동, 남, 서
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, direction);

        System.out.println(count);
    }

    private static void dfs(int r, int c, int direction) {
        if (!visited[r][c]) {
            visited[r][c] = true;
            count++;
        }

        boolean canMove = false;

        for (int i = 0; i < 4; i++) {
            direction = (direction + 3) % 4;
            int nx = r + dx[direction];
            int ny = c + dy[direction];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (!visited[nx][ny] && board[nx][ny] == 0) {
                    dfs(nx, ny, direction);
                    canMove = true;
                    break;
                }
            }
        }

        if (!canMove) {
            int backDirection = (direction + 2) % 4;
            int bx = r + dx[backDirection];
            int by = c + dy[backDirection];

            if (board[bx][by] == 1) {
                return;
            } else {
                dfs(bx, by, direction);
            }
        }
    }
}
