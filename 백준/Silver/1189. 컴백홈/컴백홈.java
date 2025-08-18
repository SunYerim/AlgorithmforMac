import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, ans = 0;
    static char[][] board;
    static boolean[][] visited;


    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        visited[R - 1][0] = true;
        backtracking(R - 1, 0, 1);

        System.out.println(ans);
    }

    public static void backtracking(int y, int x, int dis) {
        // 기저
        if (y == 0 && x == C - 1) {
            if (dis == K) {
                ans++;
            }
            return;
        }

        // 유도
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny >= 0 && nx >= 0 && ny < R && nx < C && board[ny][nx] != 'T'
                && !visited[ny][nx]) {
                visited[ny][nx] = true;
                backtracking(ny, nx, dis + 1);
                visited[ny][nx] = false;
            }
        }
    }

}
