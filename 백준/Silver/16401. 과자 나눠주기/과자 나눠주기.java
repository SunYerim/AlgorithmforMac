import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[] snacks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 조카
        N = Integer.parseInt(st.nextToken()); // 과자

        snacks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snacks);

        int low = 0;
        int high = snacks[N - 1];
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (canDistrubute(mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean canDistrubute(int length) {
        if (length == 0) {
            return true;
        }

        int cnt = 0;

        for (int snack : snacks) {
            cnt += (snack / length);
            if (cnt >= M) {
                return true;
            }
        }

        return false;
    }

}
