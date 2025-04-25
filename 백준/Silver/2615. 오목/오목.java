import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static int[] dy = {1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1};
    static int[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        board = new int[19][19];

        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = new int[3];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0) {
                    ans = go(i, j);
                    if (ans[0] != 0) {
                        System.out.println(ans[0]);
                        System.out.println(ans[1] + " " + ans[2]);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static int[] go(int y, int x) {
        int color = board[y][x];

        // 한 방향으로 오목 가능한지 판정
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            int cnt = 1;

            while (ny >= 0 && ny < 19 && nx >= 0 && nx < 19 && board[ny][nx] == color) {
                cnt++;
                ny += dy[i];
                nx += dx[i];
            }

            if (cnt == 5) {
                int prevY = y - dy[i];
                int prevX = x - dx[i];
                int nextY = y + dy[i] * 5;
                int nextX = x + dx[i] * 5;

                boolean prevSame = (prevY >= 0 && prevY < 19 && prevX >= 0 && prevX < 19
                    && board[prevY][prevX] == color);
                boolean nextSame = (nextY >= 0 && nextY < 19 && nextX >= 0 && nextX < 19
                    && board[nextY][nextX] == color);

                if (!prevSame && !nextSame) {
                    return new int[]{color, y + 1, x + 1};
                }
            }
        }

        return new int[]{0, -1, -1};

    }

}
