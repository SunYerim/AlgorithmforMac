
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dp;
    static int[][] graph;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        dp = new int[n][m][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 초깃값 설정: 첫 번째 행
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < 3; k++) {
                dp[0][j][k] = graph[0][j];
            }
        }

        // 3차원 dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 왼쪽 대각선
                if (j > 0) {
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + graph[i][j];
                } else {
                    dp[i][j][0] = Integer.MAX_VALUE;
                }
                // 윗쪽
                dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + graph[i][j];

                // 아래쪽 대각선
                if (j < m-1) {
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + graph[i][j];
                }else {
                    dp[i][j][2] = Integer.MAX_VALUE;
                }

            }

        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, Math.min(dp[n - 1][j][0], Math.min(dp[n - 1][j][1], dp[n - 1][j][2])));
        }

        System.out.println(answer);

    }

}
