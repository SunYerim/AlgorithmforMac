import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                answer++;
                dfs(i, computers, visited, n);
            }
        }


        return answer;
    }

    static void dfs(int i, int computers[][], boolean[] visited, int n) {
        visited[i] = true;

        for (int j = 0; j < n; j++) {
            if (visited[j] == false && computers[i][j] == 1) {
                dfs(j, computers, visited, n);
            }
        }
    }

}
