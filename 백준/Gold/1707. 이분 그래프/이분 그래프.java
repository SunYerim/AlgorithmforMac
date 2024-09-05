import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    static int V, E;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] colors;
    static StringBuilder sb = new StringBuilder();
    private final static int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= K; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            colors = new int[V+1];

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            boolean isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) { // 방문 안 한 노드면
                    if (!bfs(i)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1; // 첫 번째 그룹은 1로 표시합니다.

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (colors[neighbor] == 0) { // 아직 방문하지 않는 노드라면
                    colors[neighbor] = -colors[node]; // 반대 그룹으로 표시하자.
                    queue.offer(neighbor);
                } else if (colors[neighbor] == colors[node]) {
                    // 인접한 노드가 그룹이 같다면 이분 그래프가 아님
                    return false;
                }
            }
        }
        return true;
    }
}