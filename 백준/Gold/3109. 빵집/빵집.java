import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, answer = 0;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1};
    static int[] dx = {1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            if (board[i][0] == '.') {
                if (dfs(i, 0)) answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean dfs(int y, int x) {
        // 기저 -> 마지막 행
        if (x == C - 1) {
            return true;
        }

        // 유도 -> 우선 세방향
        visited[y][x] = true;

        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >= 0 && nx >= 0 && ny < R && nx < C && board[ny][nx] == '.' && !visited[ny][nx]) {
                if (dfs(ny, nx)) return true;
            }
        }
        return false;
    }

}
