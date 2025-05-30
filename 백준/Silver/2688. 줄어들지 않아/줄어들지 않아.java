import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int T, n;
    static StringBuilder sb = new StringBuilder();
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        dp = new long[65][10];

        Arrays.fill(dp[1], 1);

        for (int i = 2; i < 65; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            long ans = 0;
            for (int i = 0; i < 10; i++) {
                ans += dp[n][i];
            }

            sb.append(ans).append("\n");

        }
        System.out.println(sb.toString());

    }

}
