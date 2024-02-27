import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 1. dp 테이블 생성
        int[] dp = new int[n+1];

        // 2. 베이스값
        dp[1] = 0;
//        dp[2] = 1;
//        dp[3] = 1;

        // 3. 점화식
        for (int i = 2; i <= n; i++) {
            if (i % 6 == 0) {
                dp[i] = Math.min(dp[i/2], dp[i/3]) + 1;
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i/3], dp[i-1]) + 1;
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i-1], dp[i/2]) + 1;
            } else {
                dp[i] = dp[i-1] + 1;
            }
        }

        System.out.println(dp[n]);

    }
}