import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, B, W, ans;
    static int[] count;
    static String line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        line = br.readLine();

        ans = Integer.MIN_VALUE;
        count = new int[2]; // 0 - W, 1 - B

        int start = 0, end = 0;

        while (end < N) {
            char c = line.charAt(end);
            if (c == 'B') {
                count[1]++;
            } else {
                count[0]++;
            }

            while (count[1] > B) {
                char sc = line.charAt(start);
                if (sc == 'B') {
                    count[1]--;
                } else {
                    count[0]--;
                }
                start++;
            }

            if (count[0] >= W) {
                ans = Math.max(ans, end - start + 1);
            }

            end++;
        }

        System.out.println(ans == Integer.MIN_VALUE ? 0 : ans);

    }

}
