import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static long[][] dp;
    static long answer;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new long[31][31];
        dp[1][0] = 1;
        dp[2][0] = 2;
        dp[3][0] = 5;

        init(30, 0);

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }
            sb.append(dp[N][0]).append("\n");

        }

        System.out.print(sb.toString());
    }

    public static long init(int one, int half) {

        // 반알만 있으면
        if (one == 0) {
            return 1;
        }
        if (dp[one][half] != 0) {
            return dp[one][half];
        }

        long cnt = 0;
        // 부숨
        if (one != 0) {
            cnt += init(one - 1, half + 1);
        }
        if (half != 0) {
            cnt += init(one, half - 1);
        }

        return dp[one][half] = cnt;

    }

}
