
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] graph;
    static final int INF = (int)1e9;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 최단거리 및 정답 테이블 초기화
        graph = new int[N+1][N+1];
        ans = new int[N+1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }


        // M번만큼 입력을 받기
        // 연결관계를 작성
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        // 자기자신 0
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                }
            }
        }

        // 점화식에 따라 프롤이드 워셜 알고리즘 수행
        for (int k = 1; k <= N; k++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    graph[a][b] = Math.min(graph[a][k] + graph[k][b], graph[a][b]);
                }
            }
        }

        // 케빈 베이컨 구하기
        // 1번부터 N번까지 돌면서 더하기
        for (int i = 1; i <= N; i++) {
            int total = bacon(i);
            ans[i] = total;
        }

        // 제일 작은 번호 return
        int answer = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i <= N; i++) {
            if (ans[i] < answer) {
                answer = ans[i];
                idx = i;
            }
        }
        System.out.println(idx);


    }

    private static int bacon(int start) {
        // 본인 제외 각자에게 가는 cost를 더해 return한다
        int total = 0;
        for (int i = 1; i <= N; i++) {
            if (i != start) {
                total += graph[start][i];
            }
        }
        return total;
    }

}
