import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, L;
    static Pair[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            arr = new Pair[N+1];
            dp = new int[N+1][L+1];


            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calories = Integer.parseInt(st.nextToken());
                arr[i] = new Pair(score, calories);
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= L; j++) {
                    if (j < arr[i].calories) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i].calories] + arr[i].score);
                    }
                }
            }

            int max_score = 0;
            for (int i = 0; i <= L; i++) {
                max_score = Math.max(max_score, dp[N][i]);
            }

            System.out.println("#" + tc + " " + max_score);


        }


        // dfs -> 시간초과. dp로 수정
        // i번째 재료까지 고려했을때, 총 칼로리가 j일때의 최대 점수 -> 재료를 선택하느냐 안하느냐에서 max값과 조건을 충족하는지


    }

    static class Pair {
        int score, calories;

        public Pair(int score, int calories) {
            this.score = score;
            this.calories = calories;
        }
    }
}