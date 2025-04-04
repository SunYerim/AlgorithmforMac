import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, K;
    static int[] prices, selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        int cnt = 0;

        while (cnt++ < T) {
            sb.append("Case #").append(cnt).append("\n");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            prices = new int[N];
            selected = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            // K번에 충족하는지 확인
            selected[0] = prices[0];
            int idx = 1;

            for (int i = 1; i < N; i++) {
                if (selected[idx - 1] < prices[i]) {
                    selected[idx++] = prices[i];
                } else {
                    int pos = bs(0, idx - 1, prices[i]);
                    selected[pos] = prices[i];
                }
            }

            if (idx >= K) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb.toString());

    }

    public static int bs(int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;

            if (selected[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

}
