import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static int T, n, b, ans;
    static int[] persons;
    static boolean[] picked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            ans = Integer.MAX_VALUE;
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken()); // 최소 높이
            persons = new int[n];
            picked = new boolean[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                persons[i] = Integer.parseInt(st.nextToken());
            }
            // 점원이 한 명인 경우
            if (n==1) {
                ans = 0;
                sb.append(ans).append("\n");
                continue;
            }
            // 점원이 두 명 이상인 경우
            dfs(0, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int sum) {
        // 기저
        if (depth == n) {
            if (sum >= b) {
                ans = Math.min(ans, sum-b);
            }
            return;
        }
        // 가지치기 : 이미 구해둔 ans의 값보다 더 커진 경우는 고려하지 않는다.
        if (sum-b >= ans) return;

        dfs(depth+1, sum +persons[depth]); // 이번 순서의 점원 탑에 참여하고 다음으로
        dfs(depth+1, sum); // 이번 순서의 점원 탑에 참여 안하고 다음으로
    }
}