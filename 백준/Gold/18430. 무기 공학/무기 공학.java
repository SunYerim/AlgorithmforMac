import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans = Integer.MIN_VALUE;
    static int[][] board;
    static boolean[][] visited;
    static int[][] boomerang_dr = {{0, -1}, {0, -1}, {0, 1}, {0, 1}};
    static int[][] boomerang_dc = {{-1, 0}, {1, 0}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (N < 2 || M < 2) {
            System.out.println(0);
            return;
        }

        // backtraking
        solve(0, 0, 0);

        System.out.println(ans);

    }

    public static void solve(int r, int c, int currStrength) {
        // 기저
        if (r == N) {
            ans = Math.max(ans, currStrength);
            return;
        }

        // 유도
        int nextR = r;
        int nextC = c + 1;
        if (nextC == M) {
            nextR++;
            nextC = 0;
        }

        // 현재 칸에 부메랑을 만들지 않고 다음 칸으로
        solve(nextR, nextC, currStrength);

        // 현재 칸을 중심으로 부메랑을 만드는 경우
        // 4가지 부메랑 모양에 대해
        if (!visited[r][c]) {
            for (int i = 0; i < 4; i++) {
                int nr1 = r + boomerang_dr[i][0];
                int nc1 = c + boomerang_dc[i][0];
                int nr2 = r + boomerang_dr[i][1];
                int nc2 = c + boomerang_dc[i][1];

                // 범위 내에 있고 사용 가능한지 확인
                if (nr1 >= 0 && nr1 < N && nc1 >= 0 && nc1 < M && nr2 >= 0 && nr2 < N && nc2 >= 0
                    && nc2 < M && !visited[nr1][nc1] && !visited[nr2][nc2]) {

                    visited[r][c] = true;
                    visited[nr1][nc1] = true;
                    visited[nr2][nc2] = true;

                    int newStrength =
                        currStrength + board[nr1][nc1] + board[nr2][nc2] + (board[r][c] * 2);

                    solve(nextR, nextC, newStrength);

                    visited[r][c] = false;
                    visited[nr1][nc1] = false;
                    visited[nr2][nc2] = false;
                }
            }
        }
    }
}
