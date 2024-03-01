import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* logic
    1. 우선 한 팀만 구성해주면 다른 한 팀은 자연스럽게 배정이 된다.
    2. dfs로 한 팀 인원수가 1 ~ N/2일때를 모두 고려해서 능력치의 차이의 최솟값을 구하면 된다.
    3. */
public class Main {
    static int n;
    static int[][] map;
    static boolean[] visited;
    static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n/2; i++) {
            visited = new boolean[n];
            dfs(0, 0, i);
        }

        System.out.println(minDiff);

    }

    private static void dfs(int start, int count, int persons) {
        // 기저
        if (count == persons) {
            int team1 = 0;
            int team2 = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        team1 += map[i][j];
                    }
                    else if (!visited[i] && !visited[j]) {
                        team2 += map[i][j];
                    }
                }
            }
            int diff = Math.abs(team1 - team2);
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i+1, count+1, persons);
                visited[i] = false;
            }
        }
    }
}