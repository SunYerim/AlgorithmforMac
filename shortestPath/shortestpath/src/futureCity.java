import java.util.*;
public class futureCity {
    public static final int INF = (int) 1e9;
    // n-회사개수, m-경로개수, x-최종 목적지, k-소개팅 장소
    public static int n, m, x, k;
    // 2차원 배열
    public static int[][] graph = new int[101][101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // 무한으로 초기화
        for (int i = 0; i < 101; i++){
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신으로 가는 비용은 0으로 초기화
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                if (i==j){
                    graph[i][j] = 0;
                }
            }
        }

        // 각 간선에 대한 정보를 입력받고, -> 서로에게 가는 비용은 1로 고정되어있음.
        for (int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        // x, k 입력 받음
        x = sc.nextInt();
        k = sc.nextInt();

        // 플로이드 워셜 알고리즘 수행한다.
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    // k번째 노드를 거쳐간다.
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // distance - 출발지는 1로 고정인듯함
        int distance = graph[1][k] + graph[k][x];

        // 도달할 수 없는 경우의 case
        if (distance >= INF){
            System.out.println(-1);
        }
        // 도달되면
        else {
            System.out.println(distance);
        }
    }
}
