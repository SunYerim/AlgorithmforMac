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
        dfs(1, 0);

        System.out.println(total % 2 == 0 ? "No" : "Yes");

    }

    public static void dfs(int node, int depth) {
        visited[node] = true;
        boolean isLeaf = true;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                isLeaf = false;
                dfs(next, depth + 1);
            }
        }

        if (isLeaf) {
//            System.out.println(node + " " + depth);
            total += depth;
        }
    }


}
