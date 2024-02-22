import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parents = new int[100001];
    static ArrayList<Edge> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        int result = 0; // 나중에 반환할 값
        StringTokenizer st;

        // make - set
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph.add(new Edge(from, to, dis));
        }

        // 간선 정렬
        Collections.sort(graph);

        // 간선을 확인하면서 코드 작성
        for (int i = 0; i < graph.size(); i++) {
            int from = graph.get(i).getFrom();
            int to = graph.get(i).getTo();
            int dis = graph.get(i).getDistance();

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (find(from) != find(to)) {
                union(from, to);
                result += dis;
            }
        }
        System.out.println(result);
    }


    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b)
            parents[b] = a;
        else
            parents[a] = b;
    }

    static class Edge implements Comparable<Edge> {

        private int from, to, distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        public int getTo() {
            return this.to;
        }
        public int getFrom() {
            return this.from;
        }
        public int getDistance() {
            return this.distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
}