
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    // dfs - 대각선 이동
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};
    static int T, n, answer , startX, startY;
    static int[][] map;
    static boolean[] dessert;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            answer = -1;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 사각형을 만들 수 있는 최소범위만 확인하는데 시작점은 제일 위쪽으로 고정
            for (int i = 0; i < n-2; i++) {
                for (int j = 1; j < n-1; j++) {
                    dessert = new boolean[101];
                    dessert[map[i][j]] = true;
                    // 시작점 저장
                    startX = i;
                    startY = j;

                    // 실행
                    dfs(i, j, -1, -1, 0, 0);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y, int prevX, int prevY, int count, int dir) {
        for (int d = dir; d < 4; d++) {
            int nx = dx[d] + x;
            int ny = dy[d] + y;
            // 범위 벗어나면
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            // 또 방문하면
            if (nx == prevX && ny == prevY) continue;
            // 시작점으로 도착하면
            if (nx == startX && ny == startY) {
                // 디저트 먹은 최대 갯수 갱신
                answer = Math.max(answer, count+1);
                return;
            }
            // 이미 먹었다.
            if (dessert[map[nx][ny]]) continue;

            // 디저트 먹어
            dessert[map[nx][ny]] = true;
            dfs(nx, ny, x, y, count+1, d);
            dessert[map[nx][ny]] = false;
        }
    }
}