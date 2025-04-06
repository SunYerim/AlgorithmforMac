import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String line = br.readLine();
        dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 글자별로
        for (int i = 0; i < N - 1; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }

            char curr = line.charAt(i);
            char next = getNextChar(curr);

            for (int j = i + 1; j < N; j++) {
                if (line.charAt(j) == next) {
                    int cost = (j - i) * (j - i);
                    dp[j] = Math.min(dp[j], dp[i] + cost);
                }
            }
        }
        System.out.println(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);

    }

    public static char getNextChar(char c) {
        if (c == 'B') {
            return 'O';
        } else if (c == 'O') {
            return 'J';
        } else {
            return 'B';
        }
    }

}
