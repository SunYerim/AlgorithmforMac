import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int v, e;
    static int INF = (int) 1e9;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new int[v+1][v+1];

        for (int i = 1; i <= v; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) {
                    graph[i][j] = 0; // 자기자신 0
                }
            }
        }

        // 입력
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph[start][end] = dis;
        }

        // 플로이드-워셜
        for (int k = 1; k <= v; k++) {
            for (int a = 1; a <= v; a++) {
                for (int b = 1; b <= v; b++) {
                    if (graph[a][b] > graph[a][k] + graph[k][b]) {
                        graph[a][b] = graph[a][k] + graph[k][b];
                    }
                }
            }
        }

        // 최소 사이클의 도로 길이의 합
        int ans = INF;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (graph[i][j] > 0 && graph[j][i] > 0) {
                    ans = Math.min(ans, graph[i][j] + graph[j][i]);
                }
            }
        }

        if (ans != INF) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }
}