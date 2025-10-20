import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long K;
    static int[] level;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        level = new int[N];
        int minLevel = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(br.readLine());
            minLevel = Math.min(minLevel, level[i]);
        }

        Arrays.sort(level);

        long low = minLevel;
        long high = level[N - 1] + K;

        long answer = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (check(mid)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        System.out.println(answer);


    }

    public static boolean check(long t) {
        long needed_k = 0;
        for (int x : level) {
            if (x < t) {
                needed_k += (t - x);

                if (needed_k > K) {
                    return false;
                }
            }
        }
        return needed_k <= K;
    }

}