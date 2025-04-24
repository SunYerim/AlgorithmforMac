import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] V;
    static int[][] dp;
    static int N, S, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        V = new int[N + 1];
        dp = new int[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][S] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j] == 1) {
                    // 조건
                    if (j + V[i] <= M) {
                        dp[i][j + V[i]] = 1;
                    }
                    if (j - V[i] >= 0) {
                        dp[i][j - V[i]] = 1;
                    }
                }

            }
        }

        int answer = Integer.MIN_VALUE;

        for (int i = 0; i <= M; i++) {
            if (dp[N][i] == 1) {
                answer = Math.max(answer, i);
            }
        }

        System.out.println(answer == Integer.MIN_VALUE ? -1 : answer);


    }

}
