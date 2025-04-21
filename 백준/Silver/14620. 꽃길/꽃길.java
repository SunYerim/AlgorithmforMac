import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0, 0};
    static int[] dx = {0, 0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        dfs(0, 0);
        System.out.println(answer);

    }

    public static void dfs(int depth, int price) {
        // 기저
        if (depth == 3) {
            answer = Math.min(price, answer);
            return;
        }

        // 유도
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                // 조건 제외
                if (visited[i][j]) {
                    continue;
                }
                boolean flag = true;

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (visited[ny][nx]) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {
                    continue;
                }

                // 꽃 심고
                int total = 0;
                for (int d = 0; d < 5; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    total += board[ny][nx];
                    visited[ny][nx] = true;
                }

                dfs(depth + 1, price + total);

                // 제외하고
                for (int d = 0; d < 5; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    visited[ny][nx] = false;
                }
            }
        }
    }

}
