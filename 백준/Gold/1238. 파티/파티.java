// dijkstra
// 갔다가 돌아와야됨 ...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x;
    static int[] d, r_d;
    static ArrayList<ArrayList<Node>> graph, r_graph;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        d = new int[n+1];
        r_d = new int[n+1];
        graph = new ArrayList<>();
        r_graph = new ArrayList<>();
        // 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            r_graph.add(new ArrayList<>());
        }

        // 입력받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
            r_graph.get(to).add(new Node(from, cost));
        }

        Arrays.fill(d, INF);
        Arrays.fill(r_d, INF);

        dijkstra(graph, d, x);

        dijkstra(r_graph, r_d, x);

        int maxDis = 0;
        // 모든 노드로 가기 위한 최단 거리를 출력
        for (int i = 1; i <= n; i++) {
            maxDis = Math.max(maxDis, d[i] + r_d[i]);
        }

        System.out.println(maxDis);

    }

    private static void dijkstra(ArrayList<ArrayList<Node>> graph, int[] d, int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        d[start] = 0;
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            int now = curr.getIdx();
            int cost = curr.getCost();
            if (d[now] < cost) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost2 = d[now] + graph.get(now).get(i).getCost(); // 현재 + 거쳐가는 비용
                if (cost2 < d[graph.get(now).get(i).getIdx()]) {
                    d[graph.get(now).get(i).getIdx()] = cost2;
                    queue.offer(new Node(graph.get(now).get(i).getIdx(), cost2));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        private int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public int getIdx() {
            return idx;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}