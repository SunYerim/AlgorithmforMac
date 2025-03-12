import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static long[][] route;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        route = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(route[i], -1);
        }

        long answer = jump(0, 0);

        System.out.println(answer);

    }

    private static long jump(int y, int x) {
        // 도착지
        if (y == N - 1 && x == N - 1) {
            return 1;
        }

        // 이미 계산되어있으면
        if (route[y][x] != -1) {
            return route[y][x];
        }

        // 탐색
        route[y][x] = 0;
        int jumpSize = board[y][x];
        if (jumpSize == 0) return 0;

        for (int dir = 0; dir < 2; dir++) {
            int ny = y + dy[dir] * jumpSize;
            int nx = x + dx[dir] * jumpSize;

            if (ny < N && nx < N) {
                route[y][x] += jump(ny, nx);
            }
        }
        return route[y][x];
    }
}
