import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int a, b, N, M, answer = Integer.MAX_VALUE;
    public static int[] start, end;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        start = new int[M];
        end = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
        }

        bfs(a, b);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    public static void bfs(int st, int target) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N + 1];

        queue.add(new int[]{st, 0});
        visited[st] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == target) {
                answer = Math.min(cur[1], answer);
            }

            for (int i = 0; i < M; i++) {
                if (cur[0] == start[i] && !visited[end[i]]) {
                    queue.add(new int[]{end[i], cur[1] + 1});
                    visited[end[i]] = true;
                }
                if (cur[0] == end[i] && !visited[start[i]]) {
                    queue.add(new int[]{start[i], cur[1] + 1});
                    visited[start[i]] = true;
                }
            }
        }
    }
}
