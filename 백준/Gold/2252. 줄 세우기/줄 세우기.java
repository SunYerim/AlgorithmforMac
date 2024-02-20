import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    // 모든 노드에 대한 진입차수는 0으로 초기화
    static int[] indegree = new int[100001];
    // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    // 위상정렬
    static void topologySort() {
        ArrayList<Integer> result = new ArrayList<>();
        // bfs - 큐를 사용
        Queue<Integer> q = new LinkedList<>();

        // 진입 차수가 0인 것부터 큐에 삽입한다.
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 큐가 비어있지 않을 동안
        while (!q.isEmpty()) {
            // 현재 노드
            int now = q.poll();
            result.add(now);

            // 해당 노드에 연결된 노드들 진입차수에서 1씩 빼준다
            for (int i = 0; i < graph.get(now).size(); i++) {
                indegree[graph.get(now).get(i)] -= 1;
                // 만약 진입차수가 0이 되는 노드가 있다면 큐에 삽입
                if (indegree[graph.get(now).get(i)] == 0) {
                    q.offer(graph.get(now).get(i));
                }
            }
        }
        // 위상정렬 수행한 결과 출력
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i)+" ");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            indegree[b] += 1;
        }

        topologySort();

    }
}