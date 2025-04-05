import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, total = 0;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 관계 표현
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N + 1];
        depth = new int[N + 1];
        bfs(1);
//        dfs(1, 0);

        for (int i = 2; i <= N; i++) {
            if (graph.get(i).size() == 1) {
                total += depth[i];
            }
        }

        System.out.println(total % 2 == 0 ? "No" : "Yes");

    }

    private static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    depth[next] = depth[cur] + 1;
                }
            }
        }
    }

//    public static void dfs(int node, int depth) {
//        visited[node] = true;
//        boolean isLeaf = true;
//
//        for (int next : graph.get(node)) {
//            if (!visited[next]) {
//                isLeaf = false;
//                dfs(next, depth + 1);
//            }
//        }
//
//        if (isLeaf) {
////            System.out.println(node + " " + depth);
//            total += depth;
//        }
//    }


}
