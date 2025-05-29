import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
     * 1. 각 섬 구역 check
     * 2. 다리 연결 시도
     * 3. Min값 갱신하고 동일하면 cnt++
     * */
    static int N, ans = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandCheck();

        // 다리 연결
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) {
                    visited = new boolean[N][N];
                    int len = bridge(i, j);

                    if (len == -1) {
                        continue;
                    }
                    if (ans > len) {
                        ans = len;
                    }
                }
            }
        }

        System.out.println(ans - 1);

    }

    public static int bridge(int x, int y) {
        q = new LinkedList<>();

        int num = board[x][y];
        visited[x][y] = true;
        q.add(new int[]{x, y, 0});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int px = pos[0];
            int py = pos[1];
            int bridge = pos[2]; // 길이

            if (board[px][py] != 0 && board[px][py] != num) return bridge;

            for (int d = 0; d < 4; d++) {
                int nx = px + dx[d];
                int ny = py + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || board[nx][ny] == num) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, bridge + 1});
            }

        }
        return -1;
    }

    public static void islandCheck() {

        int idx = 2; // 영역 구분 위함
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    board[i][j] = idx;
                    visited[i][j] = true;
                    q.add(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] pos = q.poll();
                        int x = pos[0];
                        int y = pos[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                                continue;
                            }

                            if (board[nx][ny] == 1) {
                                visited[nx][ny] = true;
                                board[nx][ny] = idx;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                    idx++;
                }
            }
        }

    }

}
