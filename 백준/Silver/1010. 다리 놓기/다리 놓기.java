import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            int maxNum = Math.max(n, m);
            int[][] dp = new int[maxNum+1][maxNum+1];

            // 초기값 세팅
            for (int k = 1; k <= maxNum; k++) {
                for (int j = 1; j <= maxNum; j++) {
                    if (k == j)
                        dp[k][j] = 1;
                    if (k == 1) {
                        dp[k][j] = j;
                    }
                    if (j - k == 1) {
                        dp[k][j] = j;
                    }
                }
            }

            // dp 점화식
            for (int k = 2; k <= maxNum; k++) {
                for (int j = 4; j <= maxNum; j++) {
                    dp[k][j] = dp[k-1][j-1] + dp[k][j-1];
                }

            }

            System.out.println(dp[n][m]);
        }

    }


}