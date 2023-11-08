import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class swea1249 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // bfs로 푸는 문제 같음 -> 4방향 탐색해서 누적 값이 제일 작은 거
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int max = 999999;
        for (int i = 1; i <= T; i++) {
            int C = Integer.parseInt(br.readLine()); // 지도의 가로세로 크기 N
            int[][] map = new int[C][C];
            int[][] visited = new int[C][C];

            for (int j = 0; j < C; j++) {
                String line = br.readLine();
                for (int k = 0; k < C; k++) {
                    map[j][k] = line.charAt(k) - '0';
                    visited[j][k] = max;
                }
            }
            visited[0][0] = 0;

            int count = bfs(map, visited, C);

            System.out.println("#"+i+" "+count);

        }
    }
    public static int bfs(int[][] map, int[][] visited, int length) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int j = 0; j < 4; j++) {
                int nx = dx[j] + x;
                int ny = dy[j] + y;

                if (nx < 0 || nx >= length || ny < 0 || ny >= length) {
                    continue;
                }

                if (visited[nx][ny] > visited[x][y] + map[nx][ny]) {
                    visited[nx][ny] = visited[x][y] + map[nx][ny];
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        int answer = visited[length-1][length-1];
        return answer;
    }
}
