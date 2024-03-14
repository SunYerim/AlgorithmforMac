import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dice = {0, 0, 0, 0, 0, 0}; // 윗면, 바닥, 앞, 뒷, 왼, 오
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m, x, y, k;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            // 범위 밖으로 벗어나면 x
            int num = Integer.parseInt(st.nextToken()) - 1;
            x += dx[num];
            y += dy[num];
            if (x < 0 || y < 0 || x >= n || y >= m) {
                x -= dx[num];
                y -= dy[num];
                continue;
            }

            switch (num) {
                case 0: // 동
                    move_east();
                    break;
                case 1: // 서
                    move_west();
                    break;
                case 2: // 남
                    move_south();
                    break;
                case 3: // 북
                    move_north();
                    break;
            }

            if (map[x][y] == 0) {
                map[x][y] = dice[1];
            } else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(dice[0]);

        }
    }

    // 윗면, 바닥, 앞, 뒷, 왼, 오
    private static void move_east() {
        // 동쪽방향으로 움직이는 경우
        // 윗면 -> 오 -> 바닥 -> 왼 -> 윗
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[5];
        dice[5] = tmp;
    }

    private static void move_west() {
        // 서쪽 방향으로
        // 윗 -> 왼 -> 바닥 -> 오 -> 윗
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = tmp;
    }

    private static void move_south() {
        // 남쪽 방향으로 (아래) 안되면 north랑 변경
        // 윗 -> 앞 -> 바닥 -> 뒷 -> 윗
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[3];
        dice[3] = tmp;
    }

    private static void move_north() {
        // 북쪽 방향으로
        // 윗 -> 뒷 -> 바닥 -> 앞 -> 윗면
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[2];
        dice[2] = tmp;
    }
}