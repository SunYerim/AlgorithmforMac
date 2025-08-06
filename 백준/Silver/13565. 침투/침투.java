import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] board;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < M; i++) {
            if (board[0][i] == 0) {
                boolean flag = bfs(0, i);

                if (flag) {
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");

    }

    public static boolean bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == N - 1) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny >= 0 && nx >= 0 && ny < N && nx < M && !visited[ny][nx]
                    && board[ny][nx] == 0) {
                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }

        return false;

    }


}
