import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = (int) 1e9;
    public static int n, e, v1, v2;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n  = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];

        // 무한으로 초기화
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신으로 가는 비용은 0으로 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        // 각 간선과 cost에 대한 정보를 입력받는다.
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a][b] = cost;
            graph[b][a] = cost;
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 플로이드 워셜 알고리즘을 수행한다.
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // 특정 경로가 INF일때를 고려
        long res1 = 0;
        long res2 = 0;
        
        if (graph[1][v1] != INF && graph[v1][v2] != INF && graph[v2][n] != INF) {
            res1 = graph[1][v1] + graph[v1][v2] + graph[v2][n];
        } else {
            res1 = INF;
        }

        if (graph[1][v2] != INF && graph[v2][v1] != INF && graph[v1][n] != INF) {
            res2 = graph[1][v2] + graph[v2][v1] + graph[v1][n];
        } else {
            res2 = INF;
        }

        int distance = (int)Math.min(res1, res2);
        
        // 도달할 수 없는 경우는 -1 return
        if (distance >= INF) {
            System.out.println(-1);
        }
        // 도달 가능하면
        else {
            System.out.println(distance);
        }

    }
}