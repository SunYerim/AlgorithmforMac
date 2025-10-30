import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> cleanedKimbapLengths = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int len = arr[i];
            int cleanedLen = 0;

            if (len < k) {
                // 폐기
            } else if (len < 2 * k) {
                cleanedLen = len - k;
            } else {
                cleanedLen = len - 2 * k;
            }

            if (cleanedLen > 0) {
                cleanedKimbapLengths.add(cleanedLen);
                if (maxLen < cleanedLen) {
                    maxLen = cleanedLen;
                }
            }
        }

        if (cleanedKimbapLengths.isEmpty()) {
            System.out.println(-1);
            return;
        }

        long low = 1;
        long high = maxLen;
        long answer = -1;

        while (low <= high) {
            long mid = (low + high) / 2;
            long cnt = 0;

            for (int len : cleanedKimbapLengths) {
                cnt += len / mid;
            }

            if (cnt >= m) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(answer);
    }

}
