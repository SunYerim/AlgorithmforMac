import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, startX, startY, ans;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == '0') {
                    startX = i;
                    startY = j;
                }
            }
        }

        ans = bfs();
        System.out.println(ans);
    }
    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[n][m][64];
        // 시작점
        queue.offer(new Node(startX, startY));
        visited[startX][startY][0] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 기저
            if (map[curr.x][curr.y] == '1') {
                return curr.cost;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny][curr.key] || map[nx][ny] == '#') continue;
                // 해당 칸이 열쇠인 경우
                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int next_key = 1 << (map[nx][ny] - 'a');
                    next_key = curr.key | next_key; // or연산
                    visited[nx][ny][next_key] = true;
                    queue.offer(new Node(nx, ny, curr.cost + 1, next_key));
                }
                // 해당 칸이 문인 경우
                else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    if ((curr.key & 1 << (map[nx][ny] - 'A')) == (int)Math.pow(2, map[nx][ny] - 'A')) {
                        visited[nx][ny][curr.key] = true;
                        queue.offer(new Node(nx, ny, curr.cost + 1, curr.key));
                    }
                }

                // 나머지
                else {
                    visited[nx][ny][curr.key] = true;
                    queue.offer(new Node(nx, ny, curr.cost + 1, curr.key));
                }

            }
        }
        return -1;
    }

    static class Node {
        private int x, y, key, cost;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Node (int x, int y, int cost, int key) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.key = key;
        }
    }
}