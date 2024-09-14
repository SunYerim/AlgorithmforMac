
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] indegree;
    static int[] semester;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        semester = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            indegree[b]++;
        }
        topologySort();

        for (int i = 1; i <= n; i++) {
            System.out.print(semester[i] + " ");
        }
    }

    // 위상정렬
    private static void topologySort() {

        Queue<Integer> queue = new LinkedList<>();

        // 진입차수 0인 노드를 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                semester[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 해당 노드와 연결된 노드들 진입차수에서 1빼기
            for (int next : graph.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                    semester[next] = semester[now] + 1;
                }
            }
        }
    }
}
