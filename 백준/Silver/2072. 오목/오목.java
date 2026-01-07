import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[20][20];
    static int[] dr = {0, 1, 1, 1};
    static int[] dc = {1, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int color = (i % 2 == 1) ? 1 : 2; // 흑: 1, 백: 2
            map[r][c] = color;

            // 조건 확인
            if (checkWin(r, c, color)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);

    }

    public static boolean checkWin(int r, int c, int color) {
        // 4방향 확인
        for (int i = 0; i < 4; i++) {
            int cnt = 1;

            // 정방향
            for (int k = 1; k < 5; k++) {
                int nr = r + dr[i] * k;
                int nc = c + dc[i] * k;

                if (nr < 1 || nr > 19 || nc < 1 || nc > 19 || map[nr][nc] != color) {
                    break;
                }
                cnt++;

            }

            // 역방향
            for (int k = 1; k < 5; k++) {
                int nr = r - dr[i] * k;
                int nc = c - dc[i] * k;
                if (nr < 1 || nr > 19 || nc < 1 || nc > 19 || map[nr][nc] != color) {
                    break;
                }
                cnt++;
            }

            if (cnt == 5) {
                return true;
            }
        }

        return false;
    }

}
