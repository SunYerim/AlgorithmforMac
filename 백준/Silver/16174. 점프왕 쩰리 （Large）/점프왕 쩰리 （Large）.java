import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {0, 1}; // 오른쪽, 아래
    static int[] dx = {1, 0};

    public static void main(String[] args) throws IOException {
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

        boolean possible = bfs(0, 0);

        if (possible) {
            System.out.println("HaruHaru");
        } else {
            System.out.println("Hing");
        }
    }

    public static boolean bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        visited[startX][startY] = true;
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int count = board[y][x];

            // 기저
            if (y == N - 1 && x == N - 1) {
                return true;
            }

            for (int i = 0; i < 2; i++) {
                int ny = y + dy[i] * count;
                int nx = x + dx[i] * count;
                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {
                    continue;
                }
                queue.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }

        return false;
    }

}
