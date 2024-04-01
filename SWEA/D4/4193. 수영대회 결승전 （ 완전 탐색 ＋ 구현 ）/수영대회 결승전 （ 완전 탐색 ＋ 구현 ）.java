import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    // 소용돌이는 2초 주기 -> time % 3 == 2 일때만 이동이 가능하다.
    static int T, n;
    static int startX, startY, endX, endY;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            // bfs 메서드 실행
            int ans = bfs();
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[n][n];
        // 시작 지점을 큐에 넣어주고,
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        int time = 0;
        while (!queue.isEmpty()) {
            // 각 시간별로 탐색
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                // 4방향 탐색
                for (int i = 0; i < 4; i++) {
                    int nx = currX + dx[i];
                    int ny = currY + dy[i];
                    // 범위 밖, 이미 방문, 장애물 있으면 못 감
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || map[nx][ny] == 1) continue;
                    // 소용돌이가 있으면
                    if (map[nx][ny] == 2) {
                        // time을 3초로 나눴을 때 나머지가 2이면 소용돌이 사라졌다고 판단. 안 나눠지면 이동 불가 -> 다시 queue에 넣어주기
                        if (time % 3 != 2) {
                            // 지금 좌표 그대로 갖다넣음.
                            queue.offer(curr);
                            continue;
                        }
                    }
                    // 도착지에 도착 했으면,
                    if (nx == endX && ny == endY) {
                        return time+1;
                    }
                    // 나머지
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});

                }

            }
            time++;
        }
        // 도착 못하면
        return -1;
    }

}