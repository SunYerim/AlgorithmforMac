import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static int[] rains;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken()); // 세로
        W = Integer.parseInt(st.nextToken()); // 가로

        rains = new int [W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            rains[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;

        for (int i = 1; i < W - 1; i++) {
            // 내 기준 좌측 maxHeight
            int right = findRight(i);

            // 내 기준 우측 maxHeight
            int left = findLeft(i);

            // 그 중에 min값 - 내 높이를 total에 누적
            int minHeight = Math.min(right, left);
            if (minHeight <= rains[i]) continue;
//            System.out.println(i + " " + (minHeight - rains[i]));
            total += (minHeight - rains[i]);
        }

        System.out.println(total);
    }

    private static int findRight(int idx) {
        int ans = Integer.MIN_VALUE;
        for (int i = idx + 1; i < W; i++) {
            ans = Math.max(ans, rains[i]);
        }

        return ans;
    }

    private static int findLeft(int idx) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < idx; i++) {
            ans = Math.max(ans, rains[i]);
        }
        return ans;
    }
}
