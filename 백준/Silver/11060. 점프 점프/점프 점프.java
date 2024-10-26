import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        dp = new long[1101];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }
        
        dp[1] = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i] >= Integer.MAX_VALUE) continue;
            for (int j = 1; j <= arr[i]; j++) {
                dp[i + j] = Math.min(dp[i+j], dp[i] + 1);
            }
        }
        
        if (dp[n] >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[n]);
        }
    }

}
