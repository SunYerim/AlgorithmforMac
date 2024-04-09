import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    - 플로이드-워셜?*/
public class Main {
    static int T, n, m, ans;
    static int[][] graph;
    static int[] friends;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new int[n][n];
            // 무한으로 초기화
            for (int i = 0; i < n; i++) {
                Arrays.fill(graph[i], INF);
            }
            // 자기 자신 0으로 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    }
                }
            }
            // m개 입력받음.
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                // 양방향
                graph[from-1][to-1] = cost;
                graph[to-1][from-1] = cost;
            }
            // 사람 수
            int num = Integer.parseInt(br.readLine());
            friends = new int[num];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < num; i++) {
                friends[i] = Integer.parseInt(st.nextToken());
            }

            // 플로이드-워셜로 최단경로 갱신
            for (int k = 0; k < n; k++) {
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        // 거쳐가는 게 더 빠르면 갱신
                        if (graph[a][k] + graph[k][b] < graph[a][b]) {
                            graph[a][b] = graph[a][k] + graph[k][b];
                        }
                    }
                }
            }

            ans = Integer.MAX_VALUE;
            int tmp = 0;
            // 모임지를 하나씩 잡으면서 모임장소를 min값으로 갱신한다. -> 모임장소가
            for (int i = 0; i < n; i++) {
                int total = 0;
                // 출발지
                for (int j = 0; j < friends.length; j++) {
                    total += graph[friends[j]-1][i];
                }
                if (ans > total) {
                    ans = total;
                    tmp = i;
                }
            }
            System.out.println(tmp+1);
        }
    }


}