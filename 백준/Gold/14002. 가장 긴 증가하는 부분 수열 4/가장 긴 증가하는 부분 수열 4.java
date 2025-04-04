import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dp, selected, numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        selected = new int[N];
        numbers = new int[N];

        Arrays.fill(dp, 1);
        Arrays.fill(selected, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int maxLen = 0;
        int lastIdx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    selected[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        while (lastIdx != -1) {
            list.add(numbers[lastIdx]);
            lastIdx = selected[lastIdx];
        }
        Collections.sort(list);

        System.out.println(maxLen);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }

    }

}
