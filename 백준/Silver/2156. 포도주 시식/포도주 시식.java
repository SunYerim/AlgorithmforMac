import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 2차원 dp
    static int n;
    static int[][] dp;
    static int[] wines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        wines = new int[100001];
        dp = new int[100001][3];

        for (int i = 1; i <= n; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        // dp init
        dp[1][1] = wines[1]; // 1잔만 있을 때는 최대 값은 한 잔 마시는 것

        dp[2][0] = wines[1]; // 0번 연속
        dp[2][1] = wines[2]; // 1번 연속
        dp[2][2] = wines[1] + wines[2]; // 2번 연속

        for (int i = 3; i <= n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
            dp[i][1] = dp[i-1][0] + wines[i];
            dp[i][2] = dp[i-1][1] + wines[i];
        }

        int answer = Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]);

        System.out.println(answer);
    }

}
