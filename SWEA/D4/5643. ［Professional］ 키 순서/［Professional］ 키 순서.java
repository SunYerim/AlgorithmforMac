
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드-워셜
public class Solution {
    static int T, n, m;
    static int[][] graph;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            sb.append("#").append(tc).append(" ");

            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());
            graph = new int[n+1][n+1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // a -> b
                graph[a][b] = 1;
            }

            // 플로이드 워셜
            for (int k = 1; k <= n; k++) {
                for (int a = 1; a <= n; a++) {
                    for (int b = 1; b <= n; b++) {
                        if (graph[a][k] == 1 && graph[k][b] == 1) {
                           graph[a][b] = 1;
                        }
                    }
                }
            }

            // 키 순서 알려면 전체 학생수 - 1(n-1) 만큼 비교되어야 암. -> 4번
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                int count = 0;
                for (int j = 1; j <= n; j++) {
                    count += graph[i][j] + graph[j][i];
                }
                if (count == n-1) ans++;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
