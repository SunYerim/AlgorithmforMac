import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 모든 경우의 수를 다 고려해주어야 한다.
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][3];
        dp = new int[n][3];

        // 입력 받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초깃값 세팅
        dp[0][0] = map[0][0];
        dp[0][1] = map[0][1];
        dp[0][2] = map[0][2];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (dp[i][j] == 0) {
                    // 색상에 따라 달라짐.
                    // 빨간
                    if (j == 0) {
                        dp[i][j] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][0];
                    }
                    // 파란
                    else if (j == 1) {
                        dp[i][j] = Math.min(dp[i-1][0], dp[i-1][2]) + map[i][1];
                    }

                    // 초록
                    else if (j == 2) {
                        dp[i][j] = Math.min(dp[i-1][0], dp[i-1][1]) + map[i][2];
                    }
                }
            }

        }
        int minValue = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            if (minValue > dp[n-1][k])
                minValue = dp[n-1][k];
        }
        System.out.println(minValue);
    }
}