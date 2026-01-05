import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 101];

        Arrays.fill(dp, 100000000);

        dp[0] = 0; // 0명을 모으는 비용은 0원

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            // dp
            for (int j = people; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - people] + cost);
            }
        }

        // 적어도 C명
        int ans = Integer.MAX_VALUE;
        for (int i = C; i < dp.length; i++) {
            ans = Math.min(ans, dp[i]);
        }

        System.out.println(ans);


    }

}
