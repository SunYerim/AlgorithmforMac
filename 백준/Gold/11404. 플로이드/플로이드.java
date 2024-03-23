import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    final static int INF = (int) 1e9;
    // 최단경로 테이블
    static int[][] dis = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < 101; i++) {
            Arrays.fill(dis[i], INF);
        }

        // 본인으로 가는 건 0으로
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dis[i][j] = 0;
                }
            }
        }

        // 정보 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // cost가 더 작을 때만 갱신
            if (dis[a][b] > cost)
                dis[a][b] = cost;
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    dis[a][b] = Math.min(dis[a][b], dis[a][k] + dis[k][b]);
                }
            }
        }

        // 결과값 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 가지 못하는 경우면 0을 출력한다.
                if (dis[i][j] == INF) {
                    dis[i][j] = 0;
                }
                System.out.print(dis[i][j]+ " ");
            }
            System.out.println();
        }
    }
}