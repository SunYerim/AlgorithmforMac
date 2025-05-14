import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, ans;
    static boolean[] visited;
    static int N, total = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = new int[N];
        visited = new boolean[N];
        dfs(0);

        System.out.println(total);
    }

    public static void dfs(int cnt) {
        // 기저
        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs((ans[i] - ans[i + 1]));
            }
            total = Math.max(total, sum);
            return;
        }

        // 유도
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                ans[cnt] = arr[i];
                visited[i] = true;
                dfs(cnt + 1);
                visited[i] = false;
            }
        }
    }

}
