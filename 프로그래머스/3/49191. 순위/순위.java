import java.util.*;
class Solution {
    static int[][] graph;
    public int solution(int n, int[][] results) {
        
        graph = new int[n+1][n+1];
        // 연결관계 표현
        //System.out.println(results[0].length);
        for (int i = 0; i < results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            graph[a][b] = 1;
        }
        
        // 거쳐갈 때
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (graph[a][k] == 1 && graph[k][b] == 1)
                        graph[a][b] = 1;
                }
            }
        }
        
        // 순위 알려면 -> n-1만큼 비교
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                count += graph[i][j] + graph[j][i];
            }
            if (count == n-1) answer++;
        }

        return answer;
    }
}