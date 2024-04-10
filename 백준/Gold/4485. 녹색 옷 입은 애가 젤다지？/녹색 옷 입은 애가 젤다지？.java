import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static final int INF = (int) 1e9;
    static int[][] d;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean flag = true;
        int num = 1;
        while (flag) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                flag = false;
                break;
            }
            map = new int[n][n];
            d = new int[n][n];
            visited = new boolean[n][n];
            // 입력받기
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    d[i][j] = INF;
                }
            }

            sb.append("Problem").append(" ").append(num).append(": ");
            dijkstra(0, 0);
            sb.append(d[n-1][n-1]).append("\n");
            num++;
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra(int startX, int startY) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(startX, startY, map[startX][startY]));
        d[startX][startY] = map[startX][startY];

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (visited[curr.x][curr.y]) continue;
            visited[curr.x][curr.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    // 최단거리 갱신
                    if (d[nx][ny] > d[curr.x][curr.y] + map[nx][ny]) {
                        d[nx][ny] = d[curr.x][curr.y] + map[nx][ny];
                        queue.offer(new Node(nx, ny, d[nx][ny]));
                    }
                }
            }
        }

    }

    static class Node implements Comparable<Node>{
        private int x, y, cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}