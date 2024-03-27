import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] numbers;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n+1];
        dp = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = numbers[1];
        for (int i = 1; i <= n; i++) {
            dp[i] = numbers[i];
            for (int j = 1; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + numbers[i]);
                }
            }
        }

        int maxNum = Integer.MIN_VALUE;
        for (int i : dp) {
            maxNum = Math.max(i, maxNum);
        }
        System.out.println(maxNum);
    }

}