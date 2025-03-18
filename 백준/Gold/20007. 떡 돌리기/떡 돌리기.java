import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X, Y, answer = -1, INF = Integer.MAX_VALUE;
    static int[] d;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집
        M = Integer.parseInt(st.nextToken()); // 도로 개수
        X = Integer.parseInt(st.nextToken()); // 하루 최대 거리
        Y = Integer.parseInt(st.nextToken()); // 성현이 집

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        d = new int[N];
        Arrays.fill(d, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }

        // 최단거리 계산
        dijkstra(Y);

        Arrays.sort(d);

        boolean flag = true;

        // 가능한지 확인
        int day = 1;
        int idx = 0;
        int tmp = X;
        while (idx < N) {
            // 애초에 못 가면
            if (d[idx] * 2 > X) {
                flag = false;
                break;
            }
            // 갈 수 있으면
            if (d[idx] * 2 <= tmp) {
                tmp -= d[idx]*2;
            } else {
                day++;
                tmp = X - (d[idx] * 2);
            }
            idx++;
        }

        if (flag) {
            System.out.println(day);
        } else {
            System.out.println(-1);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cost = node.cost;
            int curr = node.end;

            if (d[curr] < cost) continue;

            for (int i = 0; i < graph.get(curr).size(); i++) {
                int nextCost = d[curr] + graph.get(curr).get(i).cost;
                if (nextCost < d[graph.get(curr).get(i).end]) {
                    d[graph.get(curr).get(i).end] = nextCost;
                    pq.offer(new Node(graph.get(curr).get(i).end, nextCost));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int end, cost;
        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}
