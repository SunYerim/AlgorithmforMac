import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0); // 초기 파이프 상태는 (0, 1) 위치에 있고, 가로 방향으로 놓여있음
        System.out.println(count);
    }

    private static void dfs(int x, int y, int dir) {
        if (x == n - 1 && y == n - 1) { // 목적지에 도착한 경우
            count++;
            return;
        }

        // 각 방향에 대해 이동 가능한지 확인하고 DFS 호출
        if (dir != 1) { // 가로 방향으로 이동하는 경우
            int nx = x;
            int ny = y + 1;
            if (ny < n && map[nx][ny] == 0) { // 이동 가능한 경우
                dfs(nx, ny, 0);
            }
        }
        if (dir != 0) { // 세로 방향으로 이동하는 경우
            int nx = x + 1;
            int ny = y;
            if (nx < n && map[nx][ny] == 0) { // 이동 가능한 경우
                dfs(nx, ny, 1);
            }
        }
        // 대각선 방향으로 이동하는 경우
        int nx = x + 1;
        int ny = y + 1;
        if (nx < n && ny < n && map[nx][ny] == 0 && map[x][ny] == 0 && map[nx][y] == 0) { // 이동 가능한 경우
            dfs(nx, ny, 2);
        }
    }
}