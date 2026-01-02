import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] graph;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        dp = new int[N][M][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // init
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, 100000000);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                dp[0][i][j] = graph[0][i];
            }
        }

        // dp
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1. 왼쪽 위
                if (j - 1 >= 0) {
                    dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + graph[i][j];
                }

                // 2. 바로 위
                dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + graph[i][j];

                // 3. 오른쪽 위
                if (j + 1 < M) {
                    dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + graph[i][j];
                }
            }
        }

        int minResult = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                if (dp[N - 1][j][k] != 0) {
                    minResult = Math.min(minResult, dp[N - 1][j][k]);
                }
            }
        }

        System.out.println(minResult);
    }

}
