import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드-워셜
public class Main {
    static int n, m;
    static int[][] graph, map;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        map = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from][to] = cost;
            graph[to][from] = cost;
        }
        // map 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = j;
            }
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        map[i][j] = map[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    sb.append('-').append(" ");
                    continue;
                }
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}