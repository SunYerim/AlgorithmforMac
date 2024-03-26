import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
- 우선 주요 문제는 각 앱을 비활성화를 시킬것이냐 말것이냐 이다.
- 그럼 dp를 생각할 때, 앱을 비활성화 시키는 경우와 시키지 않는 경우로 쪼개면 될 거 같고
- 여기서 요구하는 조건이 m바이트를 무조건 확보 해야한다는 조건이 들어간다.
- 그럼 작은 문제로 쪼개어 가면서, m이 60 -> 하나를 비활성화 시키면, m-price에 대한 문제로 dp를 실행하면 된다.

*/
public class Main {
    static int n, m, ans;
    static int[][] dp;
    static int[] memory;
    static int[] noActive;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int memories = 0;

        memory = new int[n+1];
        noActive = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());

        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            noActive[i] = Integer.parseInt(st.nextToken());
            memories += noActive[i];
        }

        dp = new int[n+1][memories+1];
        for (int i = 0; i < n+1; i++) {
            Arrays.fill(dp[i], 0);
        }

        ans = memories;

        // dp 점화식
        for (int i = 1; i <= n; i++) {
            int now = noActive[i];
            for (int j = 0; j <= memories; j++) {
                // 비활성화 할 수 있으면 -> 메모리와 비용 계산
                if (now > j) {
                    dp[i][j] = dp[i-1][j];
                }
                // 비활성화 못하면
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-now] + memory[i]);

                }
                // 초과하면
                if (dp[i][j] >= m) {
                    ans = Math.min(ans, j);
                }
            }
        }
        System.out.println(ans);
    }
}