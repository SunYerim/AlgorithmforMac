import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/* logic
    1. 절약할 수 있는 최대 비용 => 전체 돈 - 최소 비용*/
public class Main {
    static int m, n, total, result;
    static int[] parents = new int[200001];
    static ArrayList<Edge> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            // 기저
            if (m == 0 && n == 0) break;

            total = 0;
            result = 0;

            // parents 자기자신
            for (int i = 1; i <= m; i++) {
                parents[i] = i;
            }

            graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dis = Integer.parseInt(st.nextToken());
                graph.add(new Edge(from, to, dis));
            }

            for (int i = 0; i < n; i++) {
                total += graph.get(i).getDis();
            }

            Collections.sort(graph);

            for (int i = 0; i < graph.size(); i++) {
                int from = graph.get(i).getFrom();
                int to = graph.get(i).getTo();
                int dis = graph.get(i).getDis();
                // 사이클 생성이 안 되면
                if (find(from) != find(to)) {
                    union(from, to);
                    result += dis;
                }
            }
            System.out.println(total - result);
        }

    }

    private static int find(int x) {
        if (x == parents[x])
            return x;
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
        private int from, to, dis;

        public Edge(int from, int to, int dis) {
            this.from = from;
            this.to = to;
            this.dis = dis;
        }

        public int getFrom() {
            return this.from;
        }
        public int getTo() {
            return this.to;
        }
        public int getDis() {
            return this.dis;
        }
        @Override
        public int compareTo(Edge o) {
            return this.dis - o.dis;
        }
    }
}