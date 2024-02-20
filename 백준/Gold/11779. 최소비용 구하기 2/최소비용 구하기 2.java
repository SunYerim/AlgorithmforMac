import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* logic
    1. node클래스 구현
    2. 우선순위큐를 이용하여 다익스트라 알고리즘 구현
    3. main에서 최단거리테이블 무한으로 초기화, 그래프 초기화,  간선과 노드 정보 저장, 다익스트라 알고리즘 실행, 최단거리와 경로 노드 출력
        - 최단거리를 거쳐가는 노드 갯수와 노드는 list를 활용 -> 부모노드를 추적하는 배열을 추가한다.
*/
public class Main {
    static final int INF = (int) 1e9;
    static int n, m, start, end;
    static int[] d = new int[100001]; // 최단거리 테이블
    static int[] previous = new int[100001]; // 부모노드를 저장할 배열
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static ArrayList<Integer> routes = new ArrayList<>(); // 거쳐가는 노드들을 담을 리스트

    static class Node implements Comparable<Node> {

        private int index;
        private int cost; // 비용

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        public int getIndex() {
            return this.index;
        }

        public int getCost() {
            return this.cost;
        }
        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {
                return -1;
            }
            return 1;
        }

    }

    // 거쳐가는 노드들을 담을 수 있는 리스트 추가.
    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 노드로 가기 위한 최단 경로 0으로 설정
        pq.offer(new Node(start, 0));
        d[start] = 0;
//        // 시작 노드를 담고
//        routes.add(start);
        while (!pq.isEmpty()) {
            // 가장 최단 거리가 짧은 노드에 대한 정보
            Node node = pq.poll();
            int dist = node.getCost();
            int now = node.getIndex();

            // 현재 노드 이미 처리됐다면
            if (d[now] < dist) continue;

            // 현재 노드와 연결된 다른 인접 노드들을 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getCost();
                int nextNodeIndex = graph.get(now).get(i).getIndex(); // 다음 노드의 인덱스
                // 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧다면
                if (cost < d[nextNodeIndex])  {
                    d[nextNodeIndex] = cost;
                    pq.offer(new Node(nextNodeIndex, cost));
                    previous[nextNodeIndex] = now;

                }

            }
        }
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c)); // a에서 b로 가는데 c만큼의 비용이 든다.
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 최단거리 테이블 무한으로 초기화
        Arrays.fill(d,INF);

        // 다익스트라 알고리즘 수행
        dijkstra(start);

        // 경로를 역추적
        int node = end;
        while (node != 0) {
            routes.add(node);
            node = previous[node];
        }

        Collections.reverse(routes);

        System.out.println(d[end]);
        System.out.println(routes.size());
        for (int route : routes) {
            System.out.print(route+" ");
        }

    }


}