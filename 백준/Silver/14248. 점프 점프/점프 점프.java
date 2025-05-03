import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, s, ans;
    static int[] stones, d = {1, -1};
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        stones = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }
        s = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        go(s);

        System.out.println(ans);
    }

    public static void go(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        ans++;

        q.offer(start);
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < 2; i++) {
                int next = cur + stones[cur] * d[i];

                if (next >= 1 && next < stones.length && !visited[next]) {
                    ans++;
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }

    }

}
