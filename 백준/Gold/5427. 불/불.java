
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, w, h, startX, startY, ans;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] fireVisited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayDeque<Node> fires;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];
            fireVisited = new boolean[h][w];
            fires = new ArrayDeque<>();

            // 입력받기
            for (int i = 0; i < h; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = tmp.charAt(j);
                    // 이때 출발지 위치는 저장해둔다.
                    if (map[i][j] == '@') {
                        startX = i;
                        startY = j;
                    } else if (map[i][j] == '*') {
                        // 불 위치 저장
                        fires.add(new Node(i, j, 0));
                    }
                }
            }

            int result = bfs (startX, startY);
            if (result == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }
        }
    }

    private static int bfs(int startX, int startY) {
        // 상근이부터 움직이고
        Queue<Node> list = new ArrayDeque<>();
        list.offer(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!list.isEmpty()) {
            // 불 내기
            spreadFire();
            int sizes = list.size();
            for (int s = 0; s < sizes; s++) {
                Node curr = list.poll();
                int currX = curr.x;
                int currY = curr.y;

                // 기저
                if (currX == 0 || currX == h-1 || currY == 0 || currY == w-1) {
                    // 한 번 더 움직였을때 건물 밖으로 나갈 수 있으면
                    return curr.time+1;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = currX + dx[i];
                    int ny = currY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] != '#' && map[nx][ny] != '*' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        list.offer(new Node(nx, ny, curr.time+1));
                    }
                }
            }



        }

       return -1;

    }

    private static void spreadFire() {
        int fireSize = fires.size();
        for (int i = 0; i < fireSize; i++) {
            Node currFire = fires.poll();
            int currFireX = currFire.x;
            int currFireY = currFire.y;
            // 벽에는 불이 못 붙는다.
            for (int j = 0; j < 4; j++) {
                int nx = currFireX + dx[j];
                int ny = currFireY + dy[j];
                if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] != '#' && map[nx][ny] != '*') {
                    map[nx][ny] = '*';
                    fires.add(new Node(nx, ny, 0));
                }
            }
        }
    }

    static class Node{
        private int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

    }
}
