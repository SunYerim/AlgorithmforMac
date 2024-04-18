import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Node> list;
    static ArrayList<Edge> list2;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];

        // 자기 자신으로
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        list = new ArrayList<>();

        // n개만큼 입력받기
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(x, y, i));
        }

        Collections.sort(list);

        // m개만큼 입력받기
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // 나머지에 대해서 연결되어야할 통로 길이 최소가 되게끔
        double ans = 0;

        list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // 거리 정보
                double cost = distance(list.get(i), list.get(j));
                list2.add(new Edge(list.get(i).number, list.get(j).number, cost));
            }
        }

        Collections.sort(list2);

        // 크루스칼
        for (int i = 0; i < list2.size(); i++) {
            Edge edge = list2.get(i);

            if (find(edge.e) != find(edge.s)) {
                union(edge.e, edge.s);
                ans += edge.cost;
            }
        }

        System.out.println(String.format("%.2f", ans));



    }

    private static double distance(Node n1, Node n2) {
        return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
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

        if (a < b) {
            parents[b] = a;
        }
        else {
            parents[a] = b;
        }
    }

    static class Node implements Comparable<Node>{
        private int x, y, number;

        public Node(int x, int y, int number){
            this.x = x;
            this.y = y;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            return this.number - o.number;
        }
    }

    static class Edge implements Comparable<Edge> {
        private int s, e;
        private double cost;

        public Edge(int s, int e, double cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);

        }
    }
}