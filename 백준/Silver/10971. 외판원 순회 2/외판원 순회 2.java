import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// dp와 비트마스킹 이용해서 푸는 문제
public class Main {
    static int n;
    static final int INF = 16 * 1000000;
    static int dp[][];
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        // dp테이블 초기화
        dp = new int[n][(1<<n)-1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = tsp(0, 1);
        System.out.println(ans);

    }

    private static int tsp(int start, int visited) {
        // 기저 -> 다시 돌아올 때 -> visited의 요소가 모두 1일때 방문한 것
        if (visited == (1<<n)-1) {
            if (map[start][0] == 0) return INF;
            //dp[start][visited] = map[start][0];
            return map[start][0];
        }

        if (dp[start][visited] != INF) {
            return dp[start][visited];
        }

        for (int i = 0; i < n; i++) {
            // 방문한 점이라면
            if ((visited & (1 << i)) != 0 || map[start][i] == 0) // 0번은 이미 방문했음.
                continue;

            dp[start][visited] = Math.min(dp[start][visited], tsp(i, visited | (1 << i)) + map[start][i]);


        }
        return dp[start][visited];
    }


}