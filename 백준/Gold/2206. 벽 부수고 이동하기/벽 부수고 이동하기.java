import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx ={0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j) -'0';
            }
        }

        int ans = bfs();
        System.out.println(ans);
    }
    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[n][m][2];
        queue.offer(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 기저
            if (curr.x == n-1 && curr.y == m-1) {
                return curr.dist+1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                // 벽을 안 부쉈다.
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 0 && !visited[nx][ny][curr.wall]) {
                        visited[nx][ny][curr.wall] = true;
                        queue.add(new Node(nx, ny, curr.dist+1, curr.wall));
                    }
                    // 벽을 부쉈다.
                    else if (map[nx][ny] == 1 && curr.wall == 0 && !visited[nx][ny][curr.wall]) {
                        visited[nx][ny][1] = true;
                        queue.add(new Node(nx, ny, curr.dist+1, 1));
                    }
                }

            }
        }

        return -1;
    }

    static class Node {
        private int x, y, dist, wall; // wall -> 0 아직 안 부숨
        public Node (int x, int y, int dist, int wall) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.wall = wall;
        }
    }
}