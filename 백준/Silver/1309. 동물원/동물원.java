import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];

        // init
        dp[1][0] = 1;
        dp[1][1] = 2;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][0]) % 9901;
            dp[i][1] = (dp[i - 1][1] + 2 * dp[i - 1][0]) % 9901;
        }

        System.out.println((dp[N][0] + dp[N][1]) % 9901);


    }

}
