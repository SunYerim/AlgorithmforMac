import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp_up = new int[N];
        int[] dp_down = new int[N];

        Arrays.fill(dp_up, 1);
        Arrays.fill(dp_down, 1);

        // up
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp_up[i] = Math.max(dp_up[i], dp_up[j] + 1);
                }
            }
        }

        // up
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dp_down[i] = Math.max(dp_down[i], dp_down[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            maxLen = Math.max(maxLen, dp_up[i] + dp_down[i] - 1);
        }

        System.out.println(maxLen);
    }

}
