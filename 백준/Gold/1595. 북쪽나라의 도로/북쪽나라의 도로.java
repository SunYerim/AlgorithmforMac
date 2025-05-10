import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Node>> graph;
    static boolean[] visited;
    static int maxDist = 0, farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        graph = new ArrayList<>();

        for (int i = 0; i <= 10000; i++) {
            graph.add(new ArrayList<>());
        }

        String line;
        int maxNode = 0;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
            maxNode = Math.max(maxNode, Math.max(a, b));
        }

        // 최대 거리 찾기
        for (int i = 1; i <= maxNode; i++) {
            visited = new boolean[10001];
            dfs(i, 0);
        }

        System.out.println(maxDist);

    }

    public static void dfs(int node, int dist) {
        visited[node] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        for (Node next : graph.get(node)) {
            if (!visited[next.end]) {
                dfs(next.end, dist + next.dist);
            }
        }
    }

    static class Node {

        int end, dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

}
