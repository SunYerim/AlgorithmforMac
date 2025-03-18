import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static int[] schools; // M - 1, W - 2
    static List<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 도로

        parent = new int[N+1];
        schools = new int[N+1];
        edges = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            char type = st.nextToken().charAt(0);
            if (type == 'M') schools[i] = 1;
            else schools[i] = 2;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (schools[u] != schools[v]) edges.add(new Edge(u, v, d));
        }

        // sort
        Collections.sort(edges);

        int ans = 0;
        int cnt = 0;

        // 간선 확인하면서 사이클 여부 파악
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            int a = e.a;
            int b = e.b;
            int cost = e.cost;

            // 사이클
            if (findParent(a) != findParent(b)) {
                union(a, b);
                ans += cost;
                cnt++;
            }
        }

        System.out.println(cnt == N - 1 ? ans : -1);
    }

    private static int findParent(int x) {
        if (x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static class Edge implements Comparable<Edge>{
        int a, b, cost;
        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

}
