
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int n, m, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, dist));
            graph[b].add(new Node(a, dist));
        }

        // dfs 실행
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dfs(start, end, 0);
            System.out.println(max);
            max = 0;
            visited = new boolean[n+1];
        }
    }

    private static void dfs(int start, int end, int dist) {
        if (start == end) {
            max = dist;
            return;
        }

        visited[start] = true;
        for (int i = 0; i < graph[start].size(); i++) {
            if (!visited[graph[start].get(i).next]) {
                dfs(graph[start].get(i).next, end, dist + graph[start].get(i).distance);
            }
        }
    }

    static class Node {

        int next;
        int distance;

        public Node(int next, int distance) {
            this.next = next;
            this.distance = distance;
        }
    }

}
