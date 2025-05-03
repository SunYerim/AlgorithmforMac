import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // S -> E
    static int N, M, S, E, ans = Integer.MAX_VALUE;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] d = {1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs(S, E);

        System.out.println(ans);


    }

    public static void bfs(int st, int end) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N + 1];

        queue.add(new int[]{st, 0});
        visited[st] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == end) {
                ans = Math.min(cur[1], ans);
            }

            // 바로 연결
            int size = graph.get(cur[0]).size();
            for (int i = 0; i < size; i++) {
                int next = graph.get(cur[0]).get(i);
                if (!visited[next]) {
                    queue.add(new int[]{next, cur[1] + 1});
                    visited[next] = true;
                }
            }

            // 양옆
            for (int i = 0; i < 2; i++) {
                int next = cur[0] + d[i];
                if (next < 1 || next > N || visited[next]) {
                    continue;
                }
                queue.add(new int[]{next, cur[1] + 1});
                visited[next] = true;
            }


        }
    }

}
