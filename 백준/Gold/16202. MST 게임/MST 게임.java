import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;
    static List<Node> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // 턴의 갯수

        parents = new int[n + 1];
        list = new ArrayList<>();

        int level = 1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(x, y, level++));
        }

        Collections.sort(list);

        while (k > 0) {
            // init
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }

            int score = 0;
            int edgeCount = 0;

            for (Node node : list) {
                int a = node.a;
                int b = node.b;
                int cost = node.cost;

                if (find(a) != find(b)) {
                    union(a, b);
                    score += cost;
                    edgeCount++;
                }
            }

            if (edgeCount < n - 1) {
                sb.append("0 ");
            } else {
                sb.append(score).append(" ");
            }

            // 간선 제거
            if (!list.isEmpty()) {
                list.remove(0);
            }
            k--;
        }

        System.out.print(sb.toString());
    }

    public static int find(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static class Node implements Comparable<Node> {

        int a, b, cost;

        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }


    }

}
