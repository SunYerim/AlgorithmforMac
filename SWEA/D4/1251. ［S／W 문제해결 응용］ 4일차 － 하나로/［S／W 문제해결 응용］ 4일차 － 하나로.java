import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;

// 크루스칼로
public class Solution {
    static int T;
    static double e;
    static int[] parent;
    static ArrayList<Edge> edges;

    static class Edge implements Comparable<Edge> {

        private int from, to;
        private double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return this.to;
        }

        public int getFrom() {
            return this.from;
        }

        public double getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Edge o) {

            if(this.weight < o.weight) {
                return -1;
            }
            else if(this.weight > o.weight) {
                return 1;
            }
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#"+tc+" ");
            int n = Integer.parseInt(br.readLine());
            parent = new int[n+1];
            edges = new ArrayList<>();

            long[] x = new long[n+1];
            long[] y = new long[n+1];

            double result = 0;
            int cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            // 1. make-set
            for (int i = 1; i<=n; i++) {
                parent[i] = i;
            }

            e = Double.parseDouble(br.readLine());
            // edges 저장
            for (int i = 0; i < n; i++) {
                for (int j = i+1;j < n; j++) {
                    double dis = (x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j]) * (y[i]-y[j]);
                    edges.add(new Edge(i, j, dis));
                }
            }

            // 정렬
            Collections.sort(edges);

            // 모든 간선을 돌면서
            for (int i = 0; i < edges.size(); i++) {
                int from = edges.get(i).getFrom();
                int to = edges.get(i).getTo();
                double weight = edges.get(i).getWeight();
                // 사이클 발생안하면 집합 포함
                if (find(from) != find(to)) {
                    union(from, to);
                    result += weight;

                   // if (++cnt == n-1) break;
                }
            }

            double ans = result * e;

            sb.append(Math.round(ans)).append('\n');

            ans = 0;
        }
        System.out.println(sb);

    }

    // find
    public static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    // union
    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;
        else {
            if (a < b)
                parent[b] = a;
            else
                parent[a] = b;
        }
        return true;
    }
}