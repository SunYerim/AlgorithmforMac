import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] prices, selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line;
        while ((line = br.readLine()) != null && !(line = line.trim()).isEmpty()) {
            N = Integer.parseInt(line);
            prices = new int[N];
            selected = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            selected[0] = prices[0];
            int idx = 1;

            for (int i = 1; i < N; i++) {
                if (selected[idx - 1] < prices[i]) {
                    selected[idx++] = prices[i];
                } else {
                    int tmp = bs(0, idx - 1, prices[i]);
                    selected[tmp] = prices[i];
                }
            }
            sb.append(idx).append("\n");
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
