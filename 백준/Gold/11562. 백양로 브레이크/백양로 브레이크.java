import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] map1; // 문제에서 주어진 조건 그대로
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map1 = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map1[i], INF);
        }

        // 본인 -> 0
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    map1[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b == 0) {
                map1[end][start] = 1;
                map1[start][end] = 0;

            } else if (b == 1) {
                map1[start][end] = 0;
                map1[end][start] = 0;

            }
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
//                if (k == a) continue;
                for (int b = 1; b <= n; b++) {
//                    if (k == b || a == b) continue;
                    if (map1[a][k] + map1[k][b] < map1[a][b]) {
                        map1[a][b] = map1[a][k] + map1[k][b];
                    }
                }
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            System.out.println(map1[from][to]);
        }

    }
}