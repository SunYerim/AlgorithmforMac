import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, d, k, c, ans;
    static int[] sushi;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N + k - 1];
        int[] ate = new int[d + 1];

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N; i < N + k - 1; i++) {
            sushi[i] = sushi[i - N];
        }

        // init
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (ate[sushi[i]] == 0) {
                cnt++;
            }
            ate[sushi[i]]++;
        }

        int currMax = cnt;
        if (ate[c] == 0) {
            currMax++;
        }
        ans = currMax;

        // 이동
        for (int start = 1; start < N; start++) {
            int end = start + k - 1;

            ate[sushi[start - 1]]--; // 제거
            if (ate[sushi[start - 1]] == 0) {
                cnt--;
            }

            if (ate[sushi[end]] == 0) {
                cnt++;
            }

            ate[sushi[end]]++;

            currMax = cnt;
            if (ate[c] == 0) {
                currMax++;
            }
            ans = Math.max(ans, currMax);
        }

        System.out.println(ans);
    }
}
