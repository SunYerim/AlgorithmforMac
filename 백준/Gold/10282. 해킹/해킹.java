import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Computer>> graph = new ArrayList<ArrayList<Computer>>();
    static boolean[] visited;
    static int[] dis;
    static int T, n, d, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            graph.clear();

            // graph 초기화
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dis = new int[n+1];
            visited = new boolean[n+1];

            //초기화
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            // d줄에 걸쳐서 입력받음.
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Computer(to, cost));
            }

            Arrays.fill(dis, INF);
            dijkstra(c);

            int totalComputer = 0;
            int totalTime = 0;

            for (int i = 1; i <= n; i++) {
                if (dis[i] != INF) {
                    totalComputer++;
                    totalTime = Math.max(totalTime, dis[i]);
                }
            }
            System.out.println(totalComputer + " " + totalTime);

        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.offer(new Computer(start, 0));
        dis[start] = 0;
        while (!pq.isEmpty()) {
            Computer computer = pq.poll();
            int cost = computer.cost;
            int now = computer.end;
            // 이미 처리됐으면
            if (dis[now] < cost) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                int tmp = dis[now] + graph.get(now).get(i).cost;
                if (tmp < dis[graph.get(now).get(i).end]) {
                    dis[graph.get(now).get(i).end] = tmp;
                    pq.offer(new Computer(graph.get(now).get(i).end, tmp));
                }
            }
        }
    }

    static class Computer implements Comparable<Computer>{
        private int end, cost;
        public Computer(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Computer o) {
            return this.cost - o.cost;
        }
    }
}