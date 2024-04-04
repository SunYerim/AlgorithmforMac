
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
    static int T, n, ans;
    static int[][] map;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = tmp.charAt(j) - '0';
                }
            }

            // bfs 메서드 시작
            // 출발지 -> (0, 0), 도착지 -> (n-1, n-1)
            bfs(0, 0);

            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int startX, int startY) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startX, startY, map[startX][startY], map[startX][startY]));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int currX = curr.x;
            int currY = curr.y;
            // 기저
            if (currX == n-1 && currY == n-1) {
                ans = curr.total;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                // 범위 내에 있을때, 방문 안 했을때
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {

                    queue.offer(new Node(nx, ny, map[nx][ny], curr.total + map[nx][ny]));
                    visited[nx][ny] = true;
                }
            }

        }

    }

    static class Node implements Comparable<Node>{
        private int x, y, depth, total;
        public Node(int x, int y, int depth, int total) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.total = total;
        }

        @Override
        public int compareTo(Node o) {
            return this.total - o.total;
        }
    }
}
