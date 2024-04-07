import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int T, n, m, startX, startY, endX, endY, ans;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] devilVisited;
    static ArrayDeque<Node> devils;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            visited = new boolean[n][m];
            devilVisited = new boolean[n][m];
            devils = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = tmp.charAt(j);
                    // 시작점이면
                    if (map[i][j] == 'S') {
                        startX = i;
                        startY = j;
                    } else if (map[i][j] == 'D') {
                        endX = i;
                        endY = j;
                    } else if (map[i][j] == '*') {
                        devils.add(new Node(i, j, 0));
                        devilVisited[i][j] = true;
                    }
                }

            }
            boolean flag = bfs();
            if (flag) {
                sb.append(ans).append("\n");
            } else {
                sb.append("GAME OVER").append("\n");
            }
        }
        System.out.println(sb.toString());


    }

    private static boolean bfs() {
        // 수연이 이동 시키고 -> 악마 확장
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            // 악마 -> 확장
            spreadDevil();
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                // 수연
                Node curr = queue.poll();
                int x = curr.x;
                int y = curr.y;
                int time = curr.time;
                // 기저
                if (x == endX && y == endY) {
                    ans = time;
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 'X' && map[nx][ny] != '*') {
                        visited[nx][ny] = true;
                        queue.offer(new Node(nx, ny, time+1));

                    }
                }
            }

        }
        return false;

    }

    private static void spreadDevil() {
        int devilSize = devils.size();
        for (int i = 0; i < devilSize; i++) {
            Node currDevil = devils.poll();
            int devX = currDevil.x;
            int devY = currDevil.y;

            for (int j = 0; j < 4; j++) {
                int nx = devX + dx[j];
                int ny = devY + dy[j];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] != 'X' && map[nx][ny] != 'D' && map[nx][ny] != '*') {
                    map[nx][ny] = '*';
                    devils.add(new Node(nx, ny, 0));
                }

            }
        }
    }

    static class Node {
        private int x, y, time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}