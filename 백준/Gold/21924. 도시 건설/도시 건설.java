import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Edge> edges;
    static int[] parents = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        // 자기 자신으로
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        edges = new ArrayList<Edge>();

        long result = 0;
        long total = 0;


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            total += cost;
            edges.add(new Edge(from, to, cost));
        }

        Collections.sort(edges);

        int selectedEdges = 0; // 선택한 간선의 개수

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).cost;
            int from = edges.get(i).from;
            int to = edges.get(i).to;


            if (find(from) != find(to)) {
                union(from, to);
                result += cost;
                selectedEdges++;
                if (selectedEdges == n - 1) break;
            }
        }

        if (selectedEdges != n-1) {
            System.out.println(-1);
        } else {
            System.out.println(total - result);
        }

    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    private static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static class Edge implements Comparable<Edge> {
        private int from, to, cost;
        public Edge(int from ,int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}