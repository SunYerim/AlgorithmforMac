import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int[][] graph;
    static int N, T, M;
    static List<City> citys;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 수
        T = Integer.parseInt(st.nextToken()); // 텔레포트 시간
        citys = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            citys.add(new City(s, x, y));
        }

        graph = new int[N + 1][N + 1];

        // 도시간 이동거리 초기화
        for (int i = 0; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                graph[i][j] = graph[j][i] =
                    Math.abs(citys.get(i - 1).x - citys.get(j - 1).x) + Math.abs(
                        citys.get(i - 1).y - citys.get(j - 1).y
                    );

                // 텔레포트
                if (citys.get(i - 1).s == 1 && citys.get(j - 1).s == 1) {
                    graph[i][j] = graph[j][i] = Math.min(T, graph[i][j]);
                }
            }
        }

        // 플로이드 - 워셜
        for (int k = 1; k <= N; k++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(graph[s][e]).append("\n");
        }

        System.out.print(sb.toString());


    }

    static class City {

        int s, x, y;

        public City(int s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
    }

}
