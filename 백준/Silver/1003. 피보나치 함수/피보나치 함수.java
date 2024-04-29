import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] dp;
    static int T;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        // memoization
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        dp = new int[41][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }
        for (int tc = 1; tc <= T; tc++) {
            ans = new int[2];
            int num = Integer.parseInt(br.readLine());
            System.out.println(dp[num][0] + " " + dp[num][1]);

        }
    }

}