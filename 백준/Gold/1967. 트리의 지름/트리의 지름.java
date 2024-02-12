import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<ArrayList<Node>> tree;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        dist = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree.get(parent).add(new Node(child, weight));
            tree.get(child).add(new Node(parent, weight));
        }

        dfs(1, 0);
        
        int start = 1;
        for (int i = 2; i <= n; i++) {
            if (dist[i] > dist[start]) {
                start = i;
            }
        }

        dist = new int[n+1];
        visited = new boolean[n+1];
        dfs(start, 0);

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > max) {
                max = dist[i];
            }
        }

        System.out.println(max);
    }

    static void dfs(int node, int weight) {
        visited[node] = true;
        dist[node] = weight;

        for (Node n : tree.get(node)) {
            if (!visited[n.end]) {
                dfs(n.end, weight + n.weight);
            }
        }
    }

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}