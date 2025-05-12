import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board1, board2;
    static int N, M, ans = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board1 = new int[N][M];
        board2 = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board1[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board2[i][j] = line.charAt(j) - '0';
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board1[i][j] != board2[i][j] && i + 2 < N && j + 2 < M) {
                    // 3*3
                    for (int r = i; r < i + 3; r++) {
                        for (int c = j; c < j + 3; c++) {
                            board1[r][c] = board1[r][c] == 0 ? 1 : 0;
                        }
                    }
                    cnt++;
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board1[i][j] != board2[i][j]) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            ans = cnt;
        } else {
            ans = -1;
        }

        System.out.println(ans);
    }

}
