
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parents;
    static ArrayList<Edge> edges;
    static ArrayList<Edge> edges2; // 걍 union 시켜야하는거
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        int total = 0;
        edges = new ArrayList<>();
        edges2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int love = Integer.parseInt(st.nextToken());
            int valid = Integer.parseInt(st.nextToken());
            if (valid == 1) {
                edges2.add(new Edge(from, to, love, valid));
            } else if (valid == 0) {
                edges.add(new Edge(from, to, love, valid));
            }
            total += love;
        }

        // make-set
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        //Collections.sort(edges2, Collections.reverseOrder());

        int totalOne = 0;
        // 1인거는 그냥 바로 union처리
        for (int i = 0; i < edges2.size(); i++) {
            Edge curr = edges2.get(i);
            union(curr.from, curr.to);
            totalOne += curr.love;
        }

        // 0인것
        Collections.sort(edges, Collections.reverseOrder());

        // valid -> 0인것
        int result = 0;
        // 1일때, 0일때
        // union 처리 안 된 것 -> 사랑관계 포기.
        for (int i = 0; i < edges.size(); i++) {
            Edge curr = edges.get(i);
            // 성사된 관계인경우 -> 포기 x
            if (find(curr.from) != find(curr.to)) {
                union(curr.from, curr.to);
                result += curr.love;
            }

        }
        System.out.println(total - result - totalOne);
    }

    // find
    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    // union
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static class Edge implements Comparable<Edge>{
        int from, to, love, valid;
        public Edge(int from, int to, int love, int valid) {
            this.from = from;
            this.to = to;
            this.love = love;
            this.valid = valid;
        }

        @Override
        public int compareTo(Edge o) {
            return this.love - o.love;
        }
    }
}
